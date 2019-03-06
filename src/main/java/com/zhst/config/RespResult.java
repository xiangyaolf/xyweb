package com.zhst.config;

public class RespResult {

    private String code = "0";

    private String msg="成功";

    private Object data;

    public RespResult() {
    }

    public RespResult(Object data) {
        this.data = data;
    }

    public RespResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
