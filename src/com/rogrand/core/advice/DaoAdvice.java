package com.rogrand.core.advice;

import com.rogrand.sys.service.LogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;


@Service("daoAdvice")
public class DaoAdvice
  implements AfterReturningAdvice
{
  protected Log logger = LogFactory.getLog(getClass());

  public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
    if (!RightAdvice.isLog())
      return;  Object obj = objects[1];
    if (obj == null)
      return;  if (obj instanceof com.rogrand.sys.domain.Log)
      return;  String action = "";
    if (method.getName().equals("create")) { action = "插入"; }
    else if (method.getName().equals("update")) { action = "更新"; }
    else if (method.getName().equals("delete")) { action = "删除"; }
     String content = LogService.getString(obj);
    if (content != null && content.length() > 0) RightAdvice.appendContent(action + content);
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\advice\DaoAdvice.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */