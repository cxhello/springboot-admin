package com.cxhello.admin.utils;

/**
 * @author cxhello
 * @create 2019/11/7 10:01
 */
public enum StatusCodeEnum {

    SUCCESS(200,"SUCCESS"),//成功
    FAIL(400,"FAIL"),//失败
    UNAUTHORIZED(401,"UNAUTHORIZED"),//未认证（签名错误）
    NOT_FOUND(404,"NOT_FOUND"),//接口不存在
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR");//服务器内部错误

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    StatusCodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
