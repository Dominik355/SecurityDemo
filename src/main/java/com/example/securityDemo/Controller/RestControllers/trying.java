package com.example.securityDemo.Controller.RestControllers;

import com.example.securityDemo.Repositories.SessionRepo;
import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test/")
public class trying {

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private ActiveUserRepository activeUserRepository;



    /////////////////////////////////////////////////

    @GetMapping(path = {"/get/{id}"})
    public ResponseEntity get(HttpServletRequest request, @PathVariable String id) {
        return ResponseEntity.ok(this.sessionRepo.findById(id));
    }

}
