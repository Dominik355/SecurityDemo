package com.example.securityDemo.Security.securityEnums;

public enum UserPermission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    TEACHER_READ("teacher:read"),
    TEACHER_WRITE("teacher:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}

