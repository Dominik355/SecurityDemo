package com.example.securityDemo.Security.sessionManagement;

import com.sun.source.util.SourcePositions;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.io.Serializable;

@Component
public class LoggedUser implements HttpSessionBindingListener, Serializable {

    private String username;
    private ActiveUserStore activeUserStore;

    public LoggedUser(String username, ActiveUserStore activeUserStore) {
        this.username = username;
        this.activeUserStore = activeUserStore;
    }

    public LoggedUser() {}

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        LoggedUser user = (LoggedUser) event.getValue();
        System.out.println("VALUE BOUND HAS BEEN CALLED, user: " + user.getUsername());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("VALUE UNBOUND HAS BEEN CALLED, user: " + event.getName());
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
                '}';
    }
}
