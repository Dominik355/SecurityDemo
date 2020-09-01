package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Models.ActiveUser;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Service;

public class LoginStatisticService {

    @Autowired
    private ActiveUserRepository activeUserRepository;


    public static void addLoggedUser(LoggedUser loggedUser) {
        ActiveUser user = new ActiveUser(loggedUser);
    }

    public static void removeLoggedUser(LoggedUser loggedUser) {
        //PersistentTokenBasedRememberMeServices
    }

}
