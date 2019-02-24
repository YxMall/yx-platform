package com.yxmall.platform.modules.system.mapper;


import com.yxmall.platform.modules.system.entity.Qrtz_Cron_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Fired_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Job_Details;
import com.yxmall.platform.modules.system.entity.Qrtz_Triggers;

/**
 * Created by Dell on 17/02/2019.
 */
public interface QuartzMapper {
    int addJob(Qrtz_Job_Details qrtzJobDetails);//添加job
    int addTriggers(Qrtz_Triggers qrtz_triggers);//添加Triggers
    int addCron(Qrtz_Cron_Triggers qrtzCronTriggers);//添加cron表达式
    int addFiredTriggers(Qrtz_Fired_Triggers qrtz_fired_triggers);//添加需要执行的任务在qrtz_fired_triggers表
    int updateTriggers(Qrtz_Triggers qrtz_triggers);//修改触发器信息
    Qrtz_Triggers selectTriggers(Qrtz_Triggers qrtz_triggers);//通过任务名称和任务分组查询出触发器名称和分组
    int updateCron(Qrtz_Cron_Triggers qrtzCronTriggers);//修改任务执行时间
    int deleteCron(Qrtz_Cron_Triggers qrtz_cron_triggers);//删除任务的Cron
    int deleteTriggers(Qrtz_Triggers qrtz_triggers);//删除任务触发器
    int deleteJob(Qrtz_Job_Details qrtz_job_details);//删除任务
    int deleteFiredTriggers(Qrtz_Fired_Triggers qrtz_fired_triggers);//删除需要执行的任务
}
