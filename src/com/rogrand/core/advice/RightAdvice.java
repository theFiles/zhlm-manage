package com.rogrand.core.advice;

import com.rogrand.core.annotation.ActionAnnotation;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.exception.ServiceException;
import com.rogrand.core.util.WebUtil;
import com.rogrand.sys.domain.Log;
import com.rogrand.sys.domain.User;
import com.rogrand.sys.service.LogService;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;


@Service("rightAdvice")
public class RightAdvice
  extends HandlerInterceptorAdapter
  implements ServletContextAware
{
  protected org.apache.commons.logging.Log logger = LogFactory.getLog(RightAdvice.class);

  private static ThreadLocal<Log> logThreadLocal = new ThreadLocal<>();

  @Autowired
  @Qualifier("sysLogService")
  private LogService logService;

  private ServletContext servletContext;


  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }




  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String className = handler.getClass().getName();
    if (handler.getClass().isAnnotationPresent((Class) Controller.class)) {
      Controller controller = handler.getClass().<Controller>getAnnotation(Controller.class);
      className = controller.value();
    }

    String methodName = ((MultiActionController)handler).getMethodNameResolver().getHandlerMethodName(request);
    int index = methodName.lastIndexOf("_");

    methodName = (index != -1) ? methodName.substring(index + 1) : methodName;
    Class[] classes = { HttpServletRequest.class, HttpServletResponse.class };
    Method method = BeanUtils.findMethod(handler.getClass(), methodName, classes);
    if (method != null &&
      method.isAnnotationPresent((Class)ActionAnnotation.class)) {
      ActionAnnotation annotation = method.<ActionAnnotation>getAnnotation(ActionAnnotation.class);
      HttpSession session = request.getSession(true);
      User user = getLoginUser(session);
      if (annotation.check() == ActionAnnotation.Type.LOGIN) {
        if (user == null) {
          throw new ServiceException(ResponseCode.NOT_LOGIN);
        }
      } else if (annotation.check() == ActionAnnotation.Type.LOGIN_GROUP) {
        if (user == null) {
          throw new ServiceException(ResponseCode.NOT_LOGIN);
        }


        if (!user.getSu_admin().equals("1")) {
          String rightAction = className + "." + annotation.group();
          if (!checkAction(session, rightAction)) {
            throw new ServiceException(ResponseCode.NO_ACCESS);
          }
        }
      }
      if (annotation.log()) {
        Log log = new Log();
        log.setSl_class(handler.getClass().getName());
        log.setSl_method(methodName);
        log.setSl_description(annotation.name());
        log.setSl_date(new Date());
        log.setSl_ip(WebUtil.getClientIP(request));
        if (user != null) {
          log.setSl_user_code(user.getSu_code());
          log.setSl_user_name(user.getSu_name());
          log.setSl_org_name(user.getSo_name());
        }
        logThreadLocal.set(log);
      }
    }

    return true;
  }


  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    Log log = logThreadLocal.get();

    if (log != null && log.getSl_content() != null) {
      try {
        this.logService.create(log);
      } catch (Exception e) {
        this.logger.error(e.getMessage());
      }
    }

    logThreadLocal.remove();
    super.afterCompletion(request, response, handler, ex);
  }








  private User getLoginUser(HttpSession session) {
    if (session == null) return null;
    Object obj = session.getAttribute("loginUser");
    return (obj != null && obj instanceof User) ? (User)obj : null;
  }








  private boolean checkAction(HttpSession session, String rightAction) {
    if (session == null) return false;
    Object obj = session.getAttribute("loginAction");
    if (obj == null || !(obj instanceof List)) return false;
    List<String> actions = (List<String>)obj;
    return actions.contains(rightAction);
  }






  public static boolean isLog() {
    return (logThreadLocal.get() != null);
  }







  public static void appendContent(String content) throws Exception {
    Log log = logThreadLocal.get();
    if (log == null)
      return;  String text = log.getSl_content();
    if (text == null) {
      log.setSl_content(content);
    } else {
      log.setSl_content(text + "\n" + content);
    }
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\advice\RightAdvice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */