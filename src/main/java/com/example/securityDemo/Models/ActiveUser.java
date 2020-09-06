package com.example.securityDemo.Models;

import com.example.securityDemo.Security.userStatistics.LoggedUser;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * I have to use Date instead of Timestamp, because there is no default converter  for byte[] <-> Timestamp
 */
@RedisHash("active_user")
public class ActiveUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userName;
    private Date loginTime;
    private String loginType;
    @Indexed
    private String sessionId;
    private Date lastAccess;
    private String lastAddress;

    public ActiveUser(LoggedUser loggedUser) {
        this.userName = loggedUser.getUsername();
        this.loginTime = new Date(loggedUser.getLoginTime().getTime());
        this.loginType = loggedUser.getLoginType();
    }

    public ActiveUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
