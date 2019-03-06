package com.zhst.config.constant;

/**
 * 调用是否成功状态码
 */
public enum ResponseCodeEnum {

    SUCCESS("0"),FAIL("1");
    private String code;

    ResponseCodeEnum(String code) {
        this.code = code;
    }

    public String getValue() {
        return code;
    }
}
