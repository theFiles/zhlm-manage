package com.rogrand.core.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class WebUtil
{
  private static ThreadLocal<HttpServletRequest> requests = new ThreadLocal<>();
  private static ThreadLocal<HttpServletResponse> responses = new ThreadLocal<>();
  private static ServletContext servletContext = null;







  public static void setRequest(HttpServletRequest request) {
    requests.remove();
    requests.set(request);
  }






  public static HttpServletRequest getRequest() {
    return requests.get();
  }




  public static void removeRequest() {
    requests.remove();
  }






  public static void setResponse(HttpServletResponse response) {
    responses.remove();
    responses.set(response);
  }






  public static HttpServletResponse getResponse() {
    return responses.get();
  }




  public static void removeResponse() {
    responses.remove();
  }






  public static HttpSession getSession() {
    return getRequest().getSession(true);
  }







  public static void setServletContext(ServletContext context) {
    servletContext = context;
  }






  public static ServletContext getServletContext() {
    return servletContext;
  }

  public static WebApplicationContext getWebApplicationContext() {
    return WebApplicationContextUtils.getWebApplicationContext(getServletContext());
  }




  public static void removeServletContext() {
    servletContext = null;
  }


  public static String getClientIP(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    if (ip == null) {
      ip = request.getRemoteAddr();
    }

    if (ip == null) return "unknown";
    ip = ip.trim().replace("'", "");
    String[] ips = ip.split(",");
    for (String s : ips) {
      String t = s.trim();
      if (t != null && t.length() > 0) {
        return t;
      }
    }
    return "unknown";
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\WebUtil.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */