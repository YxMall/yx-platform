package com.yxmall.platform.common.utils;

import com.yxmall.platform.modules.system.entity.Qrtz_Cron_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Fired_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Job_Details;
import com.yxmall.platform.modules.system.entity.Qrtz_Triggers;
import com.yxmall.platform.modules.system.service.QuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;

/**task任务创建工具类
 * Created by Dell on 17/01/2019.
 */

@Component
public class QuartzJobManager {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobManager.class);
    private static final String TRIGGER_TYPE="cron";
    private static final String state="ACQUIRED";
    @Autowired
    QuartzService quartzService;
    private static QuartzJobManager jobUtil;
    @Autowired
    private Qrtz_Job_Details qrtz_job_details;
    @Autowired
    private Qrtz_Cron_Triggers qrtz_cron_triggers;
    @Autowired
    private Qrtz_Triggers qrtz_triggers;
    @Autowired
    private Qrtz_Fired_Triggers qrtz_fired_triggers;
    @Autowired
    private Scheduler scheduler;

    public QuartzJobManager() {
        logger.info("init jobUtil");
        jobUtil = this;
    }

    public static QuartzJobManager getInstance() {
        logger.info("return JobCreateUtil");
        return QuartzJobManager.jobUtil;
    }
    /**
     * 创建job
     *
     * @param clazz          任务类
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @param description    任务描述
     * @throws Exception
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression,String description) throws Exception {

        // 启动调度器s
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(((BaseTaskJob)clazz.newInstance()).getClass()).withIdentity(jobName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, trigger);
        qrtz_job_details.setSchedName(scheduler.getSchedulerName());
        qrtz_job_details.setJobName(jobName);
        qrtz_job_details.setJobGroup(jobGroupName);
        qrtz_job_details.setDescription(description);
        qrtz_job_details.setJobClassName(String.valueOf(jobDetail.getJobClass()));
        if(qrtz_job_details!=null) {
            int count=quartzService.addJob(qrtz_job_details);
            if(count>0) {
                qrtz_triggers.setSchedName(scheduler.getSchedulerName());
                qrtz_triggers.setTriggerName(trigger.getKey().getName());
                qrtz_triggers.setTriggerGroup(trigger.getKey().getGroup());
                qrtz_triggers.setJobName(jobName);
                qrtz_triggers.setJobGroup(jobGroupName);
                qrtz_triggers.setNextFireTime(BigInteger.valueOf(trigger.getNextFireTime().getTime()));
                qrtz_triggers.setPrevFireTime(BigInteger.valueOf(new Date().getTime()));
                qrtz_triggers.setPriority(trigger.getPriority());
                qrtz_triggers.setTriggerState(String.valueOf(scheduler.getTrigger(TriggerKey.triggerKey(trigger.getKey().getName()))));
                qrtz_triggers.setTriggerType(TRIGGER_TYPE);
                qrtz_triggers.setStartTime(BigInteger.valueOf(trigger.getStartTime().getTime()));
                int countTwo=quartzService.addTriggers(qrtz_triggers);
                if(countTwo>0){
                    qrtz_cron_triggers.setSchedName(scheduler.getSchedulerName());
                    qrtz_cron_triggers.setTriggerName(trigger.getKey().getName());
                    qrtz_cron_triggers.setCronExpression(cronExpression);
                    qrtz_cron_triggers.setTriggerGroup(trigger.getKey().getGroup());
                    qrtz_cron_triggers.setTimeZoneId(trigger.getTimeZone().getID());
                    qrtz_fired_triggers.setSchedName(scheduler.getSchedulerName());
                    qrtz_fired_triggers.setTriggerName(trigger.getKey().getName());
                    qrtz_fired_triggers.setTriggerGroup(trigger.getKey().getGroup());
                    qrtz_fired_triggers.setInstanceName(scheduler.getSchedulerInstanceId());
                    qrtz_fired_triggers.setPriority(trigger.getPriority());
                    qrtz_fired_triggers.setState(state);
                    qrtz_fired_triggers.setFiredTime(BigInteger.valueOf(new Date().getTime()));
                    qrtz_fired_triggers.setSchedTime(BigInteger.valueOf(trigger.getNextFireTime().getTime()));
                    qrtz_fired_triggers.setJobName(jobName);
                    qrtz_fired_triggers.setJobGroup(jobGroupName);
                    qrtz_fired_triggers.setEntryId(scheduler.getSchedulerInstanceId()+new Date().getTime());
                    quartzService.addCron(qrtz_cron_triggers);
                    quartzService.addFiredTriggers(qrtz_fired_triggers);
                }
            }

        }
    }


    /**
     * 创建job，可传参,这个方法主要是可以把一些需要设置的任务方法argMap集合里面，做具体的业务(如可以把任务优先级设置放到argMap集合里面)
     *
     * @param clazz          任务类
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @param argMap         map形式参数
     * @throws Exception
     */
    public void addJob(Class clazz, String jobName, String jobGroupName, String cronExpression, Map<String, Object> argMap) throws Exception {

        // 启动调度器
        scheduler.start();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(((BaseTaskJob)clazz.newInstance()).getClass()).withIdentity(jobName, jobGroupName).build();

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .withSchedule(scheduleBuilder).build();

        //获得JobDataMap，写入数据
        trigger.getJobDataMap().putAll(argMap);

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 暂停job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务所在组名称
     * @throws SchedulerException
     */
    public void pauseJob(String jobName, String jobGroupName) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
    }

    /**
     * 恢复job
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务所在组名称
     * @throws SchedulerException
     */
    public void resumeJob(String jobName, String jobGroupName) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));

    }


    /**
     * job 更新,只更新频率
     *
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @throws Exception
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);
        qrtz_triggers.setJobName(jobName);
        qrtz_triggers.setJobGroup(jobGroupName);
        qrtz_triggers.setNextFireTime(BigInteger.valueOf(trigger.getNextFireTime().getTime()));
        qrtz_triggers.setStartTime(BigInteger.valueOf(new Date().getTime()));
        Qrtz_Triggers qrtz_trigger=quartzService.selectTriggers(qrtz_triggers);
        if(qrtz_triggers!=null&&qrtz_trigger!=null) {
            quartzService.updateTriggers(qrtz_triggers);
            qrtz_cron_triggers.setCronExpression(cronExpression);
            qrtz_cron_triggers.setTriggerName(qrtz_trigger.getTriggerName());
            qrtz_cron_triggers.setTriggerGroup(qrtz_trigger.getTriggerGroup());
            quartzService.updateCron(qrtz_cron_triggers);
        }
    }


    /**
     * job 更新,更新频率和参数,这个任务更新是需要具体的业务才需要调用的，
     * 这里还没有和数据库建立关系，如有需要，请参考这个类上面的updateJob方法

     *
     * @param jobName        任务名称
     * @param jobGroupName   任务所在组名称
     * @param cronExpression cron表达式
     * @param argMap         参数
     * @throws Exception
     */
    public void updateJob(String jobName, String jobGroupName, String cronExpression, Map<String, Object> argMap) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        // 按新的cronExpression表达式重新构建trigger
        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        //修改map
        trigger.getJobDataMap().putAll(argMap);

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);

    }

    /**
     * job 更新,只更新参数
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务所在组名称
     * @param argMap       参数
     * @throws Exception
     */
    public void updateJob(String jobName, String jobGroupName, Map<String, Object> argMap) throws Exception {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        //修改map
        trigger.getJobDataMap().putAll(argMap);

        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, trigger);

    }


    /**
     * job 删除
     *
     * @param jobName      任务名称
     * @param jobGroupName 任务所在组名称
     * @throws Exception
     */
    public void deleteJob(String jobName, String jobGroupName) throws Exception {
        scheduler.pauseTrigger(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jobName, jobGroupName));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
        qrtz_triggers.setJobName(jobName);
        qrtz_triggers.setJobGroup(jobGroupName);
        Qrtz_Triggers qrtz_trigger=quartzService.selectTriggers(qrtz_triggers);
        qrtz_cron_triggers.setTriggerName(qrtz_trigger.getTriggerName());
        qrtz_cron_triggers.setTriggerGroup(qrtz_trigger.getTriggerGroup());
        int count=quartzService.deleteCron(qrtz_cron_triggers);
        if(count>0){
            qrtz_triggers.setTriggerName(qrtz_trigger.getTriggerName());
            qrtz_triggers.setTriggerGroup(qrtz_trigger.getTriggerGroup());
            qrtz_job_details.setJobName(jobName);
            qrtz_job_details.setJobGroup(jobGroupName);
            qrtz_fired_triggers.setJobName(jobName);
            qrtz_fired_triggers.setJobGroup(jobGroupName);
            quartzService.deleteTriggers(qrtz_triggers);
            quartzService.deleteJob(qrtz_job_details);
            quartzService.deleteFiredTriggers(qrtz_fired_triggers);
        }
    }


    /**
     * 启动所有定时任务
     *
     */
    public void startAllJobs() {
        try {
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     *
     */
    public void shutdownAllJobs() {
        try {
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 获取所有任务列表
     *
     * @return
     * @throws SchedulerException
     */
    public List<Map<String, Object>> getAllJob() throws SchedulerException {
        GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
        Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
        List<Map<String, Object>> jobList = new ArrayList<>();
        for (JobKey jobKey : jobKeys) {
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            for (Trigger trigger : triggers) {
                Map<String, Object> job = new HashMap<>();
                job.put("jobName", jobKey.getName());
                job.put("jobGroupName", jobKey.getGroup());
                job.put("trigger", trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                job.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    job.put("cronExpression", cronExpression);
                }
                jobList.add(job);
            }
        }
        return jobList;
    }

}


