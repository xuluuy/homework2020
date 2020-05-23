package com.xulu.result;

/**
 * @author xuluuy
 * @create 2020-01-16 18:59
 */
public class Result<T> {
    //响应码
    private int code;
    //响应信息
    private String message;
    //响应数据
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
