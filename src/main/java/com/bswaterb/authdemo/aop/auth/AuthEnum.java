package com.bswaterb.authdemo.aop.auth;

/**
 * @author bswaterb
 * @date 2022-11-04 19:14:30
 */
public enum AuthEnum {
    AUTH_STUDENT("1"),
    AUTH_TEACHER("2"),
    AUTH_ADMIN("3");

    private String value;

    private AuthEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
