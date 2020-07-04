package com.rogrand.core.quartz;



















public class QuartzJobModel
{
  String schedName;
  String jobName;
  String description;
  String targetObject;
  String targetMethod;
  String cronExpression;
  Class[] classes;
  Object[] objects;

  public String getSchedName() {
    return this.schedName;
  }

  public void setSchedName(String schedName) {
    this.schedName = schedName;
  }

  public String getJobName() {
    return this.jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public String getCronExpression() {
    return this.cronExpression;
  }

  public void setCronExpression(String cronExpression) {
    this.cronExpression = cronExpression;
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


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\quartz\QuartzJobModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */