package com.example.securityDemo.Repositories;

import com.example.securityDemo.Models.Database.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("real")
public interface UserRepository
        extends JpaRepository<User, Integer> {

    Optional<User> findByStudentName(String studentName);

}
