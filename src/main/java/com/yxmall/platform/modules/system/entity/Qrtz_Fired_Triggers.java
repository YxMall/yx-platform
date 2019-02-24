package com.yxmall.platform.modules.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**要被执行的任务将出现在此表，执行完后删除
 * Created by Dell on 17/02/2019.
 */
@Data
@Component
public class Qrtz_Fired_Triggers {
    private String schedName;//Quartz 实例名
    private String entryId;//InstanceId+系统当前毫秒
    private String triggerGroup;//Trigger 组
    private String triggerName;//Trigger 名称
    private String instanceName;//Quartz InstanceId 实例对象ID
    private BigInteger firedTime;//触发时间（系统时间
    private BigInteger schedTime;//下次fire时间
    private int priority;//任务优先级
    private String state;//状态都是“ACQUIRED”
    private String jobName;//jobdetail名字
    private String jobGroup;//Jobdetail 组
    private String isnonconcurrent;//同一任务是否并行执行
    private String requestsrecovery;//job是否恢复运行
}
