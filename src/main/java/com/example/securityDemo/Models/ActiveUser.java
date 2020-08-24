package com.example.securityDemo.Models;

import com.example.securityDemo.Security.userStatistics.LoggedUser;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@RedisHash
public class ActiveUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Timestamp loginTime;
    private String loginType;

    public ActiveUser(String name, Timestamp loginTime, String loginType) {
        this.name = name;
        this.loginTime = loginTime;
        this.loginType = loginType;
    }

    public ActiveUser(LoggedUser loggedUser) {
        this.name = loggedUser.getUsername();
        this.loginTime = loggedUser.getLoginTime();
        this.loginType = loggedUser.getHow();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
