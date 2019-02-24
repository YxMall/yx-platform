package com.yxmall.platform.modules.system.service.impl;

import com.yxmall.platform.modules.system.entity.Qrtz_Cron_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Fired_Triggers;
import com.yxmall.platform.modules.system.entity.Qrtz_Job_Details;
import com.yxmall.platform.modules.system.entity.Qrtz_Triggers;
import com.yxmall.platform.modules.system.mapper.QuartzMapper;
import com.yxmall.platform.modules.system.service.QuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dell on 17/02/2019.
 */
@Service
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    QuartzMapper quartzMapper;
    @Override
    public int addJob(Qrtz_Job_Details qrtzJobDetails) {
        return quartzMapper.addJob(qrtzJobDetails);
    }

    @Override
    public int addCron(Qrtz_Cron_Triggers qrtzCronTriggers) {
        return quartzMapper.addCron(qrtzCronTriggers);
    }

    @Override
    public int addTriggers(Qrtz_Triggers qrtz_triggers) {
        return quartzMapper.addTriggers(qrtz_triggers);
    }

    @Override
    public int addFiredTriggers(Qrtz_Fired_Triggers qrtz_fired_triggers) {
        return quartzMapper.addFiredTriggers(qrtz_fired_triggers);
    }

    @Override
    public Qrtz_Triggers selectTriggers(Qrtz_Triggers qrtz_triggers) {
        return quartzMapper.selectTriggers(qrtz_triggers);
    }

    @Override
    public int updateTriggers(Qrtz_Triggers qrtz_triggers) {
        return quartzMapper.updateTriggers(qrtz_triggers);
    }

    @Override
    public int updateCron(Qrtz_Cron_Triggers qrtzCronTriggers) {
        return quartzMapper.updateCron(qrtzCronTriggers);
    }

    @Override
    public int deleteCron(Qrtz_Cron_Triggers qrtz_cron_triggers) {
        return quartzMapper.deleteCron(qrtz_cron_triggers);
    }

    @Override
    public int deleteTriggers(Qrtz_Triggers qrtz_triggers) {
        return quartzMapper.deleteTriggers(qrtz_triggers);
    }

    @Override
    public int deleteJob(Qrtz_Job_Details qrtz_job_details) {
        return quartzMapper.deleteJob(qrtz_job_details);
    }

    @Override
    public int deleteFiredTriggers(Qrtz_Fired_Triggers qrtz_fired_triggers) {
        return quartzMapper.deleteFiredTriggers(qrtz_fired_triggers);
    }


}
