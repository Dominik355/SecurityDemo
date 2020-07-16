package com.example.securityDemo.Controller;

import com.example.securityDemo.Models.Database.User;
import com.example.securityDemo.Models.Transfer.UserRegistration;
import com.example.securityDemo.Security.MyUserDetailsService;
import com.example.securityDemo.Models.Transfer.UserDTO;
import com.example.securityDemo.Security.sessionManagement.ActiveUserStore;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class basicController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private ActiveUserStore activeUserStore;

    @GetMapping(path = {"{studentId}"})
    public UserDTO getStudent(@PathVariable int studentId) {
        return myUserDetailsService.loadStudentById(studentId);
    }

    @PostMapping(path = {"/createUser"})
    public ResponseEntity createStudent(@RequestBody UserRegistration user) {
        return ResponseEntity.ok(myUserDetailsService.createUser(user));
    }

    @GetMapping(path = {"/"})
    public List<String> getUsers() {
        return activeUserStore.getUsers();
    }

    @GetMapping(path = {"/remove"})
    public ResponseEntity getUsers(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return ResponseEntity.ok("Attribute user removed");
    }

}
