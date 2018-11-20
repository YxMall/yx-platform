package com.yxmall.platform.common.dataSource;

import java.lang.annotation.*;

/**
 * Created by Dell on 29/10/2018.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
