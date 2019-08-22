package com.qf.v16.pojo;/*
@author:g
@Date:2019/8/7
    */

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private String statusCode;
    private T data;

    public ResultBean(String statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
    public ResultBean() {

    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
