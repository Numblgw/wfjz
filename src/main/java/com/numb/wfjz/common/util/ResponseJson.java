package com.numb.wfjz.common.util;

import lombok.Data;

@Data
public class ResponseJson<T> {
    //响应的状态码
    private int code;
    //响应的信息
    private String msg;
    //响应返回的数据
    private T data;

    private ResponseJson(){
        this.code = 200;
        this.msg = "success";
    }

    private ResponseJson(T data){
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    public static ResponseJson success(){
        return new ResponseJson<>();
    }

    /**
     *  ##标记，有时间在想##
     *  这里的Object是否可以换成泛型？
     *  静态方法可以使用泛型吗，为什么？
     *
     *  是否可以（或者说是否有必要）利用反射优化new ResponseJson<>(data)这里的对象创建
     *  （创建泛型类的对象的时候需要在<>里面加上具体类型，似乎有这个规范记不清了）
     */
    public static ResponseJson success(Object data){
        return new ResponseJson<>(data);
    }
}
