package com.example.securityDemo.Repositories;

import com.example.securityDemo.Models.UserLoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

@Repository
public interface UserLoginRecordRepository extends JpaRepository<UserLoginRecord, String> {

    ArrayList<UserLoginRecord> findAllByOrderByLoginTimeAsc();

    ArrayList<UserLoginRecord> findAllByLoginTime(Timestamp loginTime);

}
