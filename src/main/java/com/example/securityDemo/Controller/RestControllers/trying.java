package com.example.securityDemo.Controller.RestControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/s")
public class trying {

    @PostMapping(path = {"/sendArray"})
    public ResponseEntity sendArray(@RequestBody Integer[][] matrix) {
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix.length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        return ResponseEntity.ok("matrix received");
    }

}
