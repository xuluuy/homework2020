package com.xulu.result;

/**
 * @author xuluuy
 * @create 2020-01-16 19:00
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//被禁用的
    NOT_FOUND(404),//没找到
    INTERNAL_SERVER_ERROR(500);//内部服务错误

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
