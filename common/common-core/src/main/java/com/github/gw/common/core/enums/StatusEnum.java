package com.github.gw.common.core.enums;

public enum StatusEnum {
    ENABLE("0","启用"),
    DISABLE("1","禁用"),
    ;

    private String code;
    private String msg;

    StatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
