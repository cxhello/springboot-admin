package com.cxhello.admin.utils;

/**
 * @author CaiXiaoHui
 * @create 2019/11/7 10:17
 */
public class ResultUtils {

    public static <T> ResultData<T> getResult(Integer code,String msg){
        return new ResultData<T>(code,msg);
    }

    public static <T> ResultData<T> getSuccessResult(){
        return new ResultData<T>(StatusCode.SUCCESS.getCode());
    }

    public static <T> ResultData<T> getFailResult(){
        return new ResultData<T>(StatusCode.FAIL.getCode());
    }

    public static <T> ResultData<T> getMsg(String msg){
        return new ResultData<T>(msg);
    }

    public static <T> ResultData<T> getSuccessResultAndData(T data){
        return new ResultData<T>(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMsg(),data);
    }
}
