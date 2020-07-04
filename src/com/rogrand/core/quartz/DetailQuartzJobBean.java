package com.rogrand.core.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

public class DetailQuartzJobBean
  extends QuartzJobBean
{
  private ApplicationContext applicationContext;
  private String targetObject;
  private String targetMethod;
  private Class[] classes;
  private Object[] objects;

  protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
    Logger logger = LoggerFactory.getLogger(DetailQuartzJobBean.class);

    try {
      Object otargetObject = this.applicationContext.getBean(this.targetObject);
      Method m = null;

      try { m = otargetObject.getClass().getMethod(this.targetMethod, this.classes);
        m.invoke(otargetObject, this.objects); }
      catch (SecurityException e) {  }
      catch (NoSuchMethodException e) {}

    }
    catch (Exception e) {

      logger.error("执行定时任务失败-", e);
    }
  }

  public String getTargetObject() {
    return this.targetObject;
  }
  public void setTargetObject(String targetObject) {
    this.targetObject = targetObject;
  }
  public String getTargetMethod() {
    return this.targetMethod;
  }
  public void setTargetMethod(String targetMethod) {
    this.targetMethod = targetMethod;
  }
  public ApplicationContext getApplicationContext() {
    return this.applicationContext;
  }
  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public Class[] getClasses() {
    return this.classes;
  }

  public void setClasses(Class[] classes) {
    this.classes = classes;
  }

  public Object[] getObjects() {
    return this.objects;
  }

  public void setObjects(Object[] objects) {
    this.objects = objects;
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\quartz\DetailQuartzJobBean.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */