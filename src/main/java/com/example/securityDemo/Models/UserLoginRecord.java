package com.example.securityDemo.Models;

import com.example.securityDemo.Models.Database.User;
import com.example.securityDemo.Security.securityEnums.LoginEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "user_login_records")
public class UserLoginRecord implements Serializable {

    @Id
    private String id;
    private String userName;
    private Timestamp loginTime;
    private Timestamp logoutTime;
    private String loginType;
    private String logoutType;

    public UserLoginRecord(ActiveUser activeUser, LoginEnum logoutType) {
        this.userName = activeUser.getUserName();
        this.loginTime = new Timestamp(activeUser.getLoginTime().getTime());
        this.logoutTime = new Timestamp(System.currentTimeMillis());
        this.loginType = activeUser.getLoginType();
        this.logoutType = logoutType.name();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public Timestamp getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Timestamp logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLogoutType() {
        return logoutType;
    }

    public void setLogoutType(String logoutType) {
        this.logoutType = logoutType;
    }
}
