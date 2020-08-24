package com.nisum.onlineRegistro.view.vo;

import lombok.Data;

import java.io.Serializable;


public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = -9212172287044278430L;
    protected T data;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
