package com.manage.util;

/**
 * Created by Administrator on 2016/6/26.
 */
public class BaseResult {

    private int errorCode;

    private String msg;

    private Object object;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
