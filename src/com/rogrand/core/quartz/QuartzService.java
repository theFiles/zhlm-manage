package com.rogrand.core.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class QuartzService
{
  @Autowired
  @Qualifier("labManagerScheduler")
  private Scheduler scheduler;

  public void addQuartzJob(QuartzJobModel job) throws SchedulerException {
    String jobName = job.getJobName();
    String targetObject = job.getTargetObject();
    String targetMethod = job.getTargetMethod();
    String description = job.getDescription();
    String cronExpression = job.getCronExpression();
    Class[] classeses = job.getClasses();
    Object[] objects = job.getObjects();


    JobDataMap dataMap = new JobDataMap();
    dataMap.put("targetObject", targetObject);
    dataMap.put("targetMethod", targetMethod);
    dataMap.put("classes", classeses);
    dataMap.put("objects", objects);
    JobDetail jobDetail = JobBuilder.newJob(DetailQuartzJobBean.class).withIdentity(jobName, "DEFAULT").withDescription(description).usingJobData(dataMap).build();




    CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(jobName + "_trigger", "DEFAULT").forJob(jobDetail).withSchedule((ScheduleBuilder) CronScheduleBuilder.cronSchedule(cronExpression)).build();




    this.scheduler.scheduleJob(jobDetail, (Trigger)trigger);
  }




  public void deleteQuartzJob(String jobName) throws SchedulerException {
    this.scheduler.deleteJob(new JobKey(jobName, "DEFAULT"));
  }
}


/* Location:              D:\file\project\tuozhanbao-manage\WEB-INF\classes\!\com.rogrand\core\quartz\QuartzService.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */