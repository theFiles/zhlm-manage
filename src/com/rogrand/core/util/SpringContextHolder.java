package com.rogrand.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder
  implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) {
    SpringContextHolder.applicationContext = applicationContext;
  }






  public static ApplicationContext getApplicationContext() {
    checkApplicationContext();
    return applicationContext;
  }






  public static <T> T getBean(String name) {
    checkApplicationContext();
    return (T)applicationContext.getBean(name);
  }







  public static <T> T getBean(Class<T> clazz) {
    checkApplicationContext();
    return (T)applicationContext.getBeansOfType(clazz);
  }

  private static void checkApplicationContext() {
    if (applicationContext == null)
      throw new IllegalStateException("applicaitonContext未注入,请在application-context.xml中定义SpringContextHolder");
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\cor\\util\SpringContextHolder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */