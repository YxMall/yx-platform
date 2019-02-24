package com.yxmall.platform.common.utils;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**基础任务调度taskJob接口，新建任务请继承此接口
 * Created by Dell on 17/01/2019.
 */
public interface BaseTaskJob extends Job {
    void execute(JobExecutionContext context)
            throws JobExecutionException;

}
