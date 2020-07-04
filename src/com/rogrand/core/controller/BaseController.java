package com.rogrand.core.controller;

import com.rogrand.core.domain.ResponseBody;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.json.JsonUtils;
import com.rogrand.sys.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;


public class BaseController
  extends MultiActionController
{
  protected UrlPathHelper urlPathHelper = new UrlPathHelper();






  protected String getServletName() {
    XmlWebApplicationContext context = (XmlWebApplicationContext)getWebApplicationContext();
    return context.getServletConfig().getServletName();
  }







  protected String getPath(HttpServletRequest request) {
    return this.urlPathHelper.getContextPath(request);
  }









  protected String getMethod(HttpServletRequest request) throws NoSuchRequestHandlingMethodException {
    String methodName = getMethodNameResolver().getHandlerMethodName(request);
    int index = methodName.lastIndexOf("_");
    return (index != -1) ? methodName.substring(index + 1) : methodName;
  }







  protected String getDomain(HttpServletRequest request) {
    String path = getPath(request);
    return request.getScheme() + "://" + request.getHeader("Host") + path;
  }







  private String takeView(HttpServletRequest request) {
    String path = this.urlPathHelper.getPathWithinApplication(request);
    int index = path.lastIndexOf(".");
    if (index != -1) {
      path = path.substring(0, index);
    }
    return path;
  }




















  protected ModelAndView getView(String view) {
    return new ModelAndView(view.startsWith("/") ? view : ("/" + view));
  }










  protected ModelAndView getView(String view, Map model) {
    return new ModelAndView(view.startsWith("/") ? view : ("/" + view), model);
  }









  protected ModelAndView getView(String view, String name, Object obj) {
    return new ModelAndView(view.startsWith("/") ? view : ("/" + view), name, obj);
  }








  protected ModelAndView getView(HttpServletRequest request) {
    String vw = takeView(request);
    return new ModelAndView(vw);
  }









  protected ModelAndView getView(HttpServletRequest request, String name, Object obj) {
    String vw = takeView(request);
    return new ModelAndView(vw, name, obj);
  }









  protected ModelAndView getView(HttpServletRequest request, Map map) {
    String vw = takeView(request);
    return new ModelAndView(vw, map);
  }














































  protected ModelAndView getRedirect(String url) {
    String vw = "redirect:" + url;
    return new ModelAndView(vw);
  }









  protected ModelAndView getRedirect(String url, String name, Object obj) {
    String vw = "redirect:" + url;
    return new ModelAndView(vw, name, obj);
  }









  protected ModelAndView getRedirect(String url, Map map) {
    String vw = "redirect:" + url;
    return new ModelAndView(vw, map);
  }







  protected ModelAndView getForward(String url) {
    String vw = "forward:" + url;
    return new ModelAndView(vw);
  }










  protected ModelAndView getForward(String url, String name, Object obj) {
    String vw = "forward:" + url;
    return new ModelAndView(vw, name, obj);
  }









  protected ModelAndView getForward(String url, Map map) {
    String vw = "forward:" + url;
    return new ModelAndView(vw, map);
  }










  protected ModelAndView responseText(HttpServletResponse response, String text) throws IOException {
    response.setContentType("text/html; charset=UTF-8");
    return new ModelAndView("/response", "data", text);
  }








  protected void responseJson(HttpServletResponse response, ResponseCode responseCode, Object content) {
    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().write(JsonUtils.toJsonString(new ResponseBody(responseCode, content)));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }








  protected void responseJson(HttpServletResponse response, ResponseCode responseCode, Object content, Map<Class<?>, String[]> includes) {
    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().write(JsonUtils.toJsonString(new ResponseBody(responseCode, content), includes));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }









  protected void responseJson(HttpServletResponse response, ResponseCode responseCode, Object content, Map<Class<?>, String[]> includes, Map<Class<?>, String[]> excludes) {
    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().write(JsonUtils.toJsonString(new ResponseBody(responseCode, content), includes, excludes));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }









  protected ModelAndView responseXML(HttpServletResponse response, String xml) throws IOException {
    response.setContentType("text/xml; charset=UTF-8");
    return new ModelAndView("/response", "data", xml);
  }








  protected void setExcelHead(HttpServletResponse response, String filename) throws IOException {
    String name = encodingFileName(filename);
    response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".xls\"");
  }







  protected String encodingFileName(String fileName) {
    String returnFileName = "";
    try {
      returnFileName = URLEncoder.encode(fileName, "UTF-8");
      if (returnFileName.length() > 150) {
        returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
      }
    } catch (UnsupportedEncodingException e) {
      this.logger.error(e.getMessage());
    }

    return returnFileName;
  }










  protected void setWordHead(HttpServletResponse response, String filename) throws IOException {
    String name = encodingFileName(filename);
    response.setContentType("application/vnd.ms-word;charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".doc\"");
  }













































  protected ModelAndView handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex, HttpServletRequest request, HttpServletResponse response) throws Exception {
    return getView(request);
  }








  protected User getLoginUser(HttpServletRequest request) {
    HttpSession session = request.getSession(true);
    return getLoginUser(session);
  }







  protected User getLoginUser(HttpSession session) {
    if (session == null) return null;
    Object obj = session.getAttribute("loginUser");
    return (obj != null && obj instanceof User) ? (User)obj : null;
  }










  protected void preHandle(HttpServletRequest request, HttpServletResponse response) throws Exception {}









  protected <T> T getBean(String id) {
    return (T)getApplicationContext().getBean(id);
  }


  protected String getBeanName() {
    String className = getClass().getName();
    if (getClass().isAnnotationPresent((Class) Controller.class)) {
      className = ((Controller)getClass().<Controller>getAnnotation(Controller.class)).value();
    }
    return className;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\controller\BaseController.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */