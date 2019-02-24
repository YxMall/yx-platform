package com.yxmall.platform.modules.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**创建的cron表达式的trigger记录实体类
 * Created by Dell on 17/02/2019.
 */
@Data
@Component
public class Qrtz_Cron_Triggers {
    private String triggerName;//Trigger名称
    private String cronExpression;//cron表达式
    private String triggerGroup;//Trigger组
    private String schedName;//Quartz 实例名
    private String TimeZoneId;//时区
}
