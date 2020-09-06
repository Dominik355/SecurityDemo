package com.example.securityDemo.Security.userStatistics;


import com.example.securityDemo.Models.ActiveUser;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.logging.Logger;

@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

    private String username;
    private Timestamp loginTime;
    private String loginType;

    public LoggedUser(String username,Timestamp loginTime, String loginType) {
        this.loginTime = new Timestamp(System.currentTimeMillis());
        this.username = username;
        this.loginType = loginType;
    }

    public LoggedUser() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public String getLoginType() {
        return loginType;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "username='" + username + '\'' +
                ", loginTime=" + loginTime +
                ", loginType=" + loginType +
                '}';
    }
}
