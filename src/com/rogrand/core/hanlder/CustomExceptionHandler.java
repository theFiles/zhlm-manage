package com.rogrand.core.hanlder;

import com.rogrand.core.domain.ResponseBody;
import com.rogrand.core.enums.ResponseCode;
import com.rogrand.core.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomExceptionHandler
  implements HandlerExceptionResolver
{
  private final Log log = LogFactory.getLog(CustomExceptionHandler.class);


  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    ResponseBody responseBody = new ResponseBody();
    if (ex instanceof ServiceException) {
      ServiceException ex1 = (ServiceException)ex;
      responseBody = new ResponseBody(ex1.getErrorCode(), ex1.getErrorDesc());
      this.log.warn("业务异常：" + ex1.getErrorDesc());
    } else {

      responseBody = new ResponseBody(ResponseCode.SYS_ERR, ex);
      this.log.error("系统异常：" + ex.getMessage(), ex);
    }

    response.setContentType("application/json;charset=UTF-8");
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0L);
    try {
      response.getWriter().write(responseBody.toJson());
    } catch (IOException e) {
      e.printStackTrace();
      this.log.error("系统异常：" + e.getMessage(), e);
    }
    return new ModelAndView();
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\hanlder\CustomExceptionHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */