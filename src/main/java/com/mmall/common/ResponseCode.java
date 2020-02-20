package com.mmall.common;

/**
 * Created by jiangyuanyuan on 23/11/17.
 */
public enum  ResponseCode {

    SUCCESS(0,"成功"),

    ERROR(1,"失败"),

    NEED_LOGIN(10,"需要登录"),

    ILLEGAL_ARGUMENT(2,"非法参数");

    private final int code;

    private final String desc;

    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return this.code;
    }

    public String getDesc(){
        return this.desc;
    }
}
