package com.example.securityDemo.Controller.RestControllers;

import com.example.securityDemo.Security.MyUserDetailsService;
import com.example.securityDemo.Models.Database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/students")
public class ManagementController {

    @Autowired
    private MyUserDetailsService userService;

    @GetMapping
    public List<User> getAllStudents() {
        return userService.getAllStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody User user) {
        System.out.println(user);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer id) {
        System.out.println("Deleting student: " + id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody User user) {
        System.out.println(String.format("%s %s", studentId, user));
    }

}
