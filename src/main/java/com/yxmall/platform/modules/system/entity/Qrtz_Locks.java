package com.yxmall.platform.modules.system.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**字典实体类，初始化两条记录，一个是获取trigger的锁，一个触发trigger的锁
 * Created by Dell on 19/02/2019.
 */
@Data
@Component
public class Qrtz_Locks {
    private String schedName;//Quartz 实例名
    private String lockName;//锁名称
}
