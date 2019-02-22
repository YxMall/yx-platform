package com.yxmall.platform.common.exception;

import com.yxmall.platform.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 异常处理类
 * @author: qing.wang.o
 * @create: 2018-09-28 13:34
 **/
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    /**
     * validate 参数校验错误处理器
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Map map = new HashMap();
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return Result.error(msgList.stream().collect(Collectors.joining(",")));
    }

    /**
     * 默认异常处理器
     *
     * @param e
     * @return
     * @Exception.class 捕获所有异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("程序发生异常：{}", e);
        return Result.error(e.getLocalizedMessage());
    }


}
