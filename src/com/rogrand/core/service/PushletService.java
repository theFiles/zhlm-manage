package com.rogrand.core.service;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.SessionManager;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("pushletService")
public class PushletService
{
  public boolean unicast(String sessionId, String eventName, Map<String, String> attributes) {
    if (SessionManager.getInstance().hasSession(sessionId)) {
      Event event = Event.createDataEvent(eventName, attributes);
      Dispatcher.getInstance().unicast(event, sessionId);
    }
    return true;
  }










  public boolean multicast(String eventName, Map<String, String> attributes) {
    Event event = Event.createDataEvent(eventName, attributes);
    Dispatcher.getInstance().multicast(event);
    return true;
  }










  public boolean broadcast(String eventName, Map<String, String> attributes) {
    Event event = Event.createDataEvent(eventName, attributes);
    Dispatcher.getInstance().broadcast(event);
    return true;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\service\PushletService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */