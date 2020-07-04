package com.rogrand.core.service;

import cn.jpush.api.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.request.CloudpushPushRequest;
import com.taobao.api.response.CloudpushPushResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class PushMessage
{
  private static String appkey = "23403470";
  private static String secret = "39ebc3c36ce79b39c07d77e3d08806e3";
  private static String url = "http://gw.api.taobao.com/router/rest";


  public static void main(String[] args) {
    Map<String, Object> params = new HashMap<>();
    params.put("page", "order");
    (new PushMessage()).pushMsg("15377005977", "发光", "测试", null);
  }









  public void pushMsg(String account, String title, String summery, Map<String, Object> params) {
    DefaultTaobaoClient defaultTaobaoClient = new DefaultTaobaoClient(url, appkey, secret);
    CloudpushPushRequest req = new CloudpushPushRequest();
    if (StringUtils.isNotEmpty(account)) {
      req.setTarget("account");
      req.setTargetValue(account);
    } else {
      req.setTarget("all");
      req.setTargetValue("all");
    }
    req.setType(new Long(1L));
    req.setAndroidActivity("/store/...");
    req.setAndroidExtParameters(JSON.toJSONString(params));

    req.setAndroidMusic("default");
    req.setAndroidOpenType("1");
    req.setAndroidOpenUrl("http://www.taobao.com");
    req.setAntiHarassDuration(Long.valueOf(13L));
    req.setAntiHarassStartTime(Long.valueOf(1L));
    req.setBatchNumber("0001");
    req.setBody(summery);

    req.setDeviceType(Long.valueOf(3L));

    req.setIosBadge("1");
    req.setIosExtParameters(JSON.toJSONString(params));


    req.setIosMusic("default");
    req.setRemind(Boolean.valueOf(false));
    req.setStoreOffline(Boolean.valueOf(false));
    req.setSummery(summery);
    req.setTimeout(Long.valueOf(72L));
    req.setTitle(title);
    try {
      CloudpushPushResponse response = (CloudpushPushResponse)defaultTaobaoClient.execute((TaobaoRequest)req);
      if (response.isSuccess()) {
        System.out.println("push message is success!");
      }
    } catch (Exception e) {
      System.out.println("push message is error!");
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\PushMessage.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */