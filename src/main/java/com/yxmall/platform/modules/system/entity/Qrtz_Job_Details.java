package com.yxmall.platform.modules.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**job属性表，维护job的基础信息
 * Created by Dell on 17/02/2019.
 */
@Data
@Component
public class Qrtz_Job_Details {
    private String schedName;////Quartz 实例名
    private String jobName;//job的名字
    private String description;//job的描述
    private String jobClassName;//job实现类的完全包名,quartz就是根据这个路径到classpath找到该job类
    private String jobGroup;//job的所属组的名字
    private String isDurable;//持久job
    private String isNonconcurrent;//并发JOB @DisallowConcurrentExecution
    private String isUpdateData;//持久jobDataMap @PersistJobDataAfterExecution
    private String requestsRecovery;//job是否恢复运行
    private byte[] jobData;//Job的datamap里的数据





}
