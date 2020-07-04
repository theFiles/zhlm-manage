package com.rogrand.core.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class JPushService
{
  protected Log logger = LogFactory.getLog(getClass());

  @Value("${masterSecret}")
  private String masterSecret;

  @Value("${appKey}")
  private String appKey;

  private JPushClient jpushClient = null;








  public void push(String userId, String alert, String title, Map<String, String> extras) {
    if (this.jpushClient == null) {
      this.jpushClient = new JPushClient(this.masterSecret, this.appKey);
    }

    if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(alert)) {
      return;
    }
    try {
      List<String> alias = new ArrayList<>();
      alias.add(userId);

      PushPayload pushPayload = buildPushObject_all_alias_alertWithTitle(alias, alert, title, extras);
      PushResult pushResult = this.jpushClient.sendPush(pushPayload);
      System.out.println(pushResult);
    } catch (Exception e) {
      this.logger.error("推送失败:" + e.getMessage());
      e.printStackTrace();
    }
  }








  public void push(List<String> userIds, String alert, String title, Map<String, String> extras) {
    if (this.jpushClient == null) {
      this.jpushClient = new JPushClient(this.masterSecret, this.appKey);
    }

    if (userIds == null || userIds.size() == 0 || StringUtils.isEmpty(alert)) {
      return;
    }
    try {
      List<String> alias = new ArrayList<>();
      for (int i = 0; i < userIds.size(); i++) {
        alias.add(userIds.get(i));
      }

      PushPayload pushPayload = buildPushObject_all_alias_alertWithTitle(alias, alert, title, extras);
      PushResult result = this.jpushClient.sendPush(pushPayload);
      System.out.println(result);
    } catch (Exception e) {
      this.logger.error("推送失败:" + e.getMessage());
      e.printStackTrace();
    }
  }







  public void pushAll(String alert, String title, Map<String, String> extras) {
    if (this.jpushClient == null) {
      this.jpushClient = new JPushClient(this.masterSecret, this.appKey);
    }

    try {
      PushPayload pushPayload = buildPushObject_all_all_alert(alert, title, extras);
      PushResult result = this.jpushClient.sendPush(pushPayload);
      System.out.println(result);
    } catch (Exception e) {
      this.logger.error("推送失败:" + e.getMessage());
      e.printStackTrace();
    }
  }









  public PushPayload buildPushObject_all_alias_alertWithTitle(List<String> alias, String alert, String title, Map<String, String> extras) {
    AndroidNotification androidNotification = AndroidNotification.newBuilder().setAlert(alert).setTitle(title).addExtras(extras).build();
    IosNotification iosNotification = IosNotification.newBuilder().setAlert(alert).setContentAvailable(true).addExtras(extras).build();
    Options options = Options.newBuilder().setApnsProduction(true).build();

    return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias)).setOptions(options).setNotification(Notification.newBuilder().addPlatformNotification((PlatformNotification)androidNotification).addPlatformNotification((PlatformNotification)iosNotification).build()).build();
  }











  public static PushPayload buildPushObject_all_all_alert(String alert, String title, Map<String, String> extras) {
    AndroidNotification androidNotification = AndroidNotification.newBuilder().setAlert(alert).setTitle(title).addExtras(extras).build();
    IosNotification iosNotification = IosNotification.newBuilder().setAlert(alert).setContentAvailable(true).addExtras(extras).build();
    Options options = Options.newBuilder().setApnsProduction(true).build();

    return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all()).setOptions(options).setNotification(Notification.newBuilder().addPlatformNotification((PlatformNotification)androidNotification).addPlatformNotification((PlatformNotification)iosNotification).build()).build();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\JPushService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */