package com.example.securityDemo.Controller.RestControllers;

import com.example.securityDemo.Models.Car;
import com.example.securityDemo.Models.RedisModel;
import com.example.securityDemo.Repositories.CarRepo;
import com.example.securityDemo.Repositories.RedisRepo;
import com.example.securityDemo.Repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test/")
public class trying {

    @Autowired
    private RedisRepo redisRepo;

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private SessionRepo sessionRepo;

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

    @GetMapping(path = {"/getSessions"})
    public ResponseEntity getSessions(HttpServletRequest request) {
        return ResponseEntity.ok(this.redisRepo.getModels());
    }

    @PostMapping(path = {"/postSession"})
    public ResponseEntity postSession(@RequestBody RedisModel redisModel) {
        return ResponseEntity.ok(this.redisRepo.saveModel(redisModel));
    }

    @GetMapping(path = {"/getSession/{id}"})
    public ResponseEntity getSession(HttpServletRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(this.redisRepo.getModel(id));
    }


////////////******************************************////////////////////////////////////////


    @GetMapping(path = {"/getCars"})
    public ResponseEntity getCars(HttpServletRequest request) {
        return ResponseEntity.ok(this.carRepo.getCars());
    }

    @PostMapping(path = {"/postCar"})
    public ResponseEntity postCar(@RequestBody Car car) {
        return ResponseEntity.ok(this.carRepo.saveCar(car));
    }

    @GetMapping(path = {"/getCar/{id}"})
    public ResponseEntity getCar(HttpServletRequest request, @PathVariable Integer id) {
        return ResponseEntity.ok(this.carRepo.getCar(id));
    }

    /////////////////////////////////////////////////

    @GetMapping(path = {"/get/{id}"})
    public ResponseEntity get(HttpServletRequest request, @PathVariable String id) {
        return ResponseEntity.ok(this.sessionRepo.findById(id));
    }

}
