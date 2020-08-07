package com.example.securityDemo.Security.userStatistics;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActiveUserStore {

    public List<LoggedUser> users;

    public ActiveUserStore() {
        users = new ArrayList<>();
    }

    public List<LoggedUser> getUsers() {
        return users;
    }

    public void addUser(LoggedUser user) {
        this.users.add(user);
    }

    public List<String> showUsers() {
        return users.stream().map(u -> u.toString()).collect(Collectors.toList());
    }

}
