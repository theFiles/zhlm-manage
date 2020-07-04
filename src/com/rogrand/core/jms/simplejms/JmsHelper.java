package com.rogrand.core.jms.simplejms;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JmsHelper
{
  public static Map<Integer, WebNode> webNodes = new HashMap<>();

  public static void loadPushConfig(String configPath) {
    try {
      SAXReader reader = new SAXReader();
      Document document = reader.read(JmsHelper.class.getResourceAsStream(configPath));
      List<?> elements = document.getRootElement().elements();

      webNodes.clear();
      for (Object e : elements) {
        if (e instanceof Element) {
          Element et = (Element)e;
          WebNode target = new WebNode();
          target.setId(Integer.parseInt(et.attributeValue("id")));
          target.setPort(Integer.parseInt(et.attributeValue("port")));
          target.setProtocol(et.attributeValue("protocol"));
          target.setHost(et.attributeValue("host"));
          target.setApp(et.attributeValue("app"));
          webNodes.put(Integer.valueOf(target.getId()), target);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("加载消息节点配置文件出错:" + e.getMessage(), e);
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\jms\simplejms\JmsHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */