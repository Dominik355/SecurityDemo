package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Models.ActiveUser;
import com.example.securityDemo.Models.UserLoginRecord;
import com.example.securityDemo.Repositories.UserLoginRecordRepository;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import com.example.securityDemo.Security.securityEnums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LoginStatisticService {

    @Autowired
    private ActiveUserRepository activeUserRepository;

    @Autowired
    private UserLoginRecordRepository recordRepository;

    public void addLoginRecord(ActiveUser activeUser, LoginEnum logoutType) {
        recordRepository.save(new UserLoginRecord(activeUser, logoutType));
        System.out.println("Login record added for user: " + activeUser.getUserName());
    }

    public ArrayList<UserLoginRecord> getLoginRecords() {
        return new ArrayList<>(recordRepository.findAllByOrderByLoginTimeAsc());
    }

    public ArrayList<UserLoginRecord> getLoginRecords(Timestamp date) {
        return recordRepository.findAllByLoginTime(date)
                .stream()
                .sorted(Comparator.comparing(UserLoginRecord::getLoginTime))
                .collect(Collectors.toCollection(() -> new ArrayList<>()));
    }

}
