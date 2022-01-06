package com.github.gw.gateway.common;

/**
 * 操作类型枚举
 *
 * @author li7hai26@gmail.com
 * @date 2021/12/17
 */
public enum ActionEnum {

    REQUEST("REQUEST", "请求"),
    RESPONSE("RESPONSE", "响应"),
    CREATE("CREATE", "保存"),
    UPDATE("UPDATE", "更新"),
    DELETE("DELETE", "删除"),
    ;

    String code;
    String msg;

    ActionEnum(String code, String msg) {
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
