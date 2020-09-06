package com.example.securityDemo.Controller.RestControllers;

import com.example.securityDemo.Models.ActiveUser;
import com.example.securityDemo.Models.Transfer.UserRegistration;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import com.example.securityDemo.Security.MyUserDetailsService;
import com.example.securityDemo.Models.Transfer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class basicController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping(path = {"{studentId}"})
    public UserDTO getStudent(@PathVariable int studentId) {
        return myUserDetailsService.loadStudentById(studentId);
    }

    @PostMapping(path = {"/createUser"})
    public ResponseEntity createStudent(@RequestBody UserRegistration user) {
        return ResponseEntity.ok(myUserDetailsService.createUser(user));
    }

}
