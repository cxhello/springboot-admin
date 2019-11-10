package com.cxhello.admin.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CaiXiaoHui
 * @create 2019/11/6 16:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResultData(Integer code){
        this.code = code;
    }

    public ResultData(String msg){
        this.msg = msg;
    }

    public ResultData(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
