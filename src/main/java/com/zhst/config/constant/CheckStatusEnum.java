package com.zhst.config.constant;

/**
 * 生产报表-发电量指标-审核状态枚举
 */
public enum CheckStatusEnum {
    NO_CHECK("未审核"),PASS("合格"),NO_PASS("不合格");
    //状态值
    String value;

    CheckStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
