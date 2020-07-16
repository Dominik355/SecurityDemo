package com.example.securityDemo.Models.Database;

import com.example.securityDemo.Models.Transfer.UserRegistration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

import static com.example.securityDemo.Security.authorization.UserRole.*;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer studentId;
    private String studentName;
    private String password;
    private String role;

    public User(String studentName, String role) {
        this.studentName = studentName;
        this.role = ADMIN.name();
    }

    public User(String studentName, String role, String password) {
        this.studentName = studentName;
        this.role = ADMIN.name();
        this.password = password;
    }

    public User(UserRegistration userRegistration) {
        this.studentName = userRegistration.getUsername();
        this.password = userRegistration.getPassword();
        this.role = userRegistration.getRole();
    }

    public User() {

    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ID: " + studentId +
                ", NAME: " + studentName +
                ", PASSWORD: " + password +
                ", ROLE: " + role;
    }

}
