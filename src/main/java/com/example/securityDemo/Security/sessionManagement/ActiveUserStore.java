package com.example.securityDemo.Security.sessionManagement;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
public class ActiveUserStore implements Serializable {

    public List<String> users;

    public ActiveUserStore() {
        this.users = new ArrayList<>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void addUser(String user) {
        this.users.add(user);
    }

}
