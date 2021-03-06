package com.yxmall.platform.common.utils;


import com.yxmall.platform.common.constant.CommonConstant;
import com.yxmall.platform.common.emuns.ResponseStatusEnum;

import java.util.HashMap;

/**
 * 返回数据
 *
 * @author smile
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;


    public static Result result(ResponseStatusEnum status) {
        Result result = new Result();
        result.put("code", status.getCode());
        result.put("msg", status.getMsg());
        return result;
    }


    /**
     * sysuse
     * 默认成功
     *
     * @return
     */
    public static Result success() {
        Result result = Result.result(ResponseStatusEnum.SUCCESS);
        return result;
    }

    /**
     * 自定义成功信息返回
     *
     * @param msg
     * @return
     */
    public static Result success(String msg) {
        Result result = Result.success();
        result.put("msg", msg);
        return result;
    }


    /**
     * 成功返回带参
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = Result.success();
        result.put("data", data);
        return result;
    }


    /**
     * 自定义成功信息返回带参
     *
     * @param data
     * @return
     */
    public static Result success(String msg, Object data) {
        Result result = Result.success();
        result.put("msg", msg);
        result.put("data", data);
        return result;
    }


    /**
     * 默认错误返回
     *
     * @return
     */
    public static Result error() {
        Result result = Result.result(ResponseStatusEnum.INNER_ERROR);
        return result;
    }

    /**
     * 自定义失败返回信息方法
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        Result result = Result.result(ResponseStatusEnum.INNER_ERROR);
        result.put("msg", msg);
        return result;
    }


    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }


    /**
     * 是否成功
     *
     * @param flag
     * @return
     */
    public static Result isSuccess(boolean flag) {
        return flag ? Result.success() :
                Result.error();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return this.get("code").equals(ResponseStatusEnum.SUCCESS.getCode());
    }

    /**
     * 是否失败
     *
     * @return
     */
    public boolean isError() {
        return !this.get("code").equals(ResponseStatusEnum.SUCCESS.getCode());
    }
}
