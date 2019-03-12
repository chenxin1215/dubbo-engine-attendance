package com.cx.login.entity.common;

import java.util.EnumMap;

public enum LoginStatusEnum {

    /*登陆成功*/
    LOGIN_SUCCESS(1),
    /*账号不存在*/
    ACCOUNT_NULL(0),
    /*密码错误*/
    PWD_ERROR(-1),
    /*账号已停用*/
    ACCOUNT_DISABLE(-2),
    /*系统错误*/
    SYS_ERROR(-3);

    private Integer value;

    private static final EnumMap<LoginStatusEnum, String> TEXT_MAP =
        new EnumMap<LoginStatusEnum, String>(LoginStatusEnum.class);
    static {
        TEXT_MAP.put(LOGIN_SUCCESS, "登陆成功");
        TEXT_MAP.put(ACCOUNT_NULL, "账号不存在");
        TEXT_MAP.put(PWD_ERROR, "密码错误");
        TEXT_MAP.put(ACCOUNT_DISABLE, "账号已停用");
        TEXT_MAP.put(SYS_ERROR, "系统错误");
    }

    private LoginStatusEnum(Integer value) {
        this.value = value;
    }

    public static LoginStatusEnum prase(Integer value) {
        for (LoginStatusEnum loginStatusEnum : LoginStatusEnum.values()) {
            if (loginStatusEnum.value == value) {
                return loginStatusEnum;
            }
        }
        throw new RuntimeException("无法解析的支出类别值 value=" + value);
    }

    public Integer value() {
        return value;
    }

    public String toString() {
        return TEXT_MAP.get(this);
    }

}
