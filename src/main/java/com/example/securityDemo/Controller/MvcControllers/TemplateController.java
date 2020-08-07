package com.example.securityDemo.Controller.MvcControllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("courses_student")
    public String getCoursesStudent() {
        return "courses_student";
    }

    @PreAuthorize("hasRole('ROLE_TEACHER') or hasRole('ROLE_ADMIN')")
    @GetMapping("courses_teacher")
    public String getCoursesTeacher() {
        return "courses_teacher";
    }

}
