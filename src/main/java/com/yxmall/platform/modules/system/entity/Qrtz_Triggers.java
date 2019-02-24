package com.yxmall.platform.modules.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * Created by Dell on 17/02/2019.
 */
@Data
@Component
public class Qrtz_Triggers {
    private String schedName;////Quartz 实例名
    private String triggerName;//trigger的名字
    private String triggerGroup;//trigger组
    private String jobName;//Job 名称
    private String jobGroup;//Job 组
    private String description;//描述
    private BigInteger nextFireTime;//下次触发时间 毫秒
    private BigInteger prevFireTime;
    private int priority;//权重
    private BigInteger startTime;//开始运行时间
    private BigInteger endTime;//结束运行时间
    private String calendarName;//设置的calendar的名称
    private String triggerState;//当前trigger状态，设置为ACQUIRED,如果设置为WAITING,则job不会触发
    private String triggerType;//触发器类型,使用cron表达式
    private byte[] jobData;//JOB的datamap,仓库ID存在此datamap里
    private Integer misfireInstr;//-1:错过执行 0:正常



}
