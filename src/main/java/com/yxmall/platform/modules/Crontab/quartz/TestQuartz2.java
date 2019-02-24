package com.yxmall.platform.modules.Crontab.quartz;

import com.yxmall.platform.common.utils.BaseTaskJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Dell on 18/01/2019.
 */
@Component
public class TestQuartz2 implements BaseTaskJob {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("这是任务:2" );
    }
}
