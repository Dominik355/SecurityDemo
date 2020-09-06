package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Models.ActiveUser;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import com.example.securityDemo.Security.securityEnums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.sql.Date;

@Service
public class ActiveUsersService {

    @Autowired
    private ActiveUserRepository activeUserRepository;

    @Autowired
    private LoginStatisticService loginStatisticService;

    public void addActiveUser(LoggedUser loggedUser, String sessionId) {
        ActiveUser activeUser = new ActiveUser(loggedUser);
        activeUser.setLastAccess(new Date(loggedUser.getLoginTime().getTime()));
        activeUser.setLastAddress("/login");
        activeUser.setSessionId(sessionId);
        if(loggedUser.getUsername() == LoginEnum.VISITOR.name()) {
            activeUser.setUserName(LoginEnum.VISITOR.name());
        }
        activeUserRepository.save(activeUser);
    }

    public void removeActiveUser(String sessionID, LoginEnum logoutType) {
        try {
            System.out.println("sessionID: " + sessionID + ", ActiveUser: " + activeUserRepository.findBySessionId(sessionID));
            createRecord(activeUserRepository.findBySessionId(sessionID), logoutType);
            activeUserRepository.deleteBySessionId(sessionID);
            System.out.println("active user deleted: " + sessionID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ActiveUser> getActiveUsers() {
        return StreamSupport.stream(activeUserRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(ActiveUser::getUserName))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

    private void createRecord(ActiveUser activeUser, LoginEnum logoutType) {
        loginStatisticService.addLoginRecord(activeUser, logoutType);
    }

}
