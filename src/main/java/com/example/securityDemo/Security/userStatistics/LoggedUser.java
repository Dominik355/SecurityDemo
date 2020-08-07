package com.example.securityDemo.Security.userStatistics;


import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;
import java.sql.Timestamp;

@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

    private String username;
    private Timestamp loginTime;
    private ActiveUserStore activeUserStore;

    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.activeUserStore = activeUserStore;
        this.loginTime = new Timestamp(System.currentTimeMillis());
        this.username = username;
    }

    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        activeUserStore.getUsers().stream().filter(u -> u.getUsername().equals(((LoggedUser) event.getValue()).getUsername()));
        activeUserStore.addUser((LoggedUser) event.getValue());
        System.out.println("value BOUND called: " + ((LoggedUser) event.getValue()).getUsername());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        this.activeUserStore.getUsers().remove(((LoggedUser) event.getValue()).getUsername());
        System.out.println("value UN-BOUND called: " + ((LoggedUser) event.getValue()).getUsername());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ActiveUserStore getActiveUserStore() {
        return activeUserStore;
    }

    public void setActiveUserStore(ActiveUserStore activeUserStore) {
        this.activeUserStore = activeUserStore;
    }

    @Override
    public String toString() {
        return "LoggedUser{" +
                "username='" + username + '\'' +
                ", loginTime=" + loginTime +
                '}';
    }
}
