package com.rogrand.core.controller;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.service.PushletService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller("pushController")
@RequestMapping({"/push/*"})
public class PushController
  extends BaseController
{
  protected static Log logger = LogFactory.getLog(PushController.class);








  @Autowired
  @Qualifier("pushletService")
  private PushletService pushletService;









  @ActionAnnotation(name = "Pushlet推送消息", group = "消息推送", log = true, check = ActionAnnotation.Type.NO)
  public void pushWebMsg(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String sessionId = request.getParameter("sessionId");

    String eventName = StringUtils.defaultString(request.getParameter("eventName"), "/kkmy_manager/push");

    String pushletType = StringUtils.defaultString(request.getParameter("pushletType"), "unicast");

    String pushType = StringUtils.defaultString(request.getParameter("pushType"), "unknow");

    String pushContent = StringUtils.defaultString(request.getParameter("pushContent"), "{}");

    logger.info("Pushlet推送消息: sessionId=" + sessionId + ", eventName=" + eventName + ", pushletType=" + pushletType + ", pushType=" + pushType + ", pushContent=" + pushContent);



    Map<String, String> attributes = new HashMap<>();
    attributes.put("pushType", pushType);
    attributes.put("pushContent", pushContent);


    if ("unicast".equalsIgnoreCase(pushletType)) {
      this.pushletService.unicast(sessionId, eventName, attributes);
    } else if ("broadcast".equalsIgnoreCase(pushletType)) {
      this.pushletService.broadcast(eventName, attributes);
    } else if ("multicast".equalsIgnoreCase(pushletType)) {
      this.pushletService.multicast(eventName, attributes);
    } else {
      throw new Exception("Undefined pushletType:" + pushletType);
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\controller\PushController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */