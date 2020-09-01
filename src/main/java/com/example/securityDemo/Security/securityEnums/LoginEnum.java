package com.example.securityDemo.Security.securityEnums;

import java.util.EnumSet;

public enum LoginEnum {

    LOGIN,
    REMEMBER_ME,
    USER,
    VISITOR;

    public static EnumSet<LoginEnum> loginType = EnumSet.of(LOGIN, REMEMBER_ME);
    public static EnumSet<LoginEnum> userType = EnumSet.of(USER, VISITOR);

}