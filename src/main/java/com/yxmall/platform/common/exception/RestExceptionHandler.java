package com.yxmall.platform.common.exception;

import com.yxmall.platform.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 异常处理类
 * @author: qing.wang.o
 * @create: 2018-09-28 13:34
 **/
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    /**
     * 默认异常处理器
     * @Exception.class 捕获所有异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.error(e.getMessage());
    }


}
