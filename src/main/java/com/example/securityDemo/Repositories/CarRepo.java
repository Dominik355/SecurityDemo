package com.example.securityDemo.Repositories;

import com.example.securityDemo.Models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class CarRepo {

    @Autowired
    private HashOperations hashOperations;

    private final String key = "CAR";

    public String saveCar(Car car) {
        hashOperations.put(key, car.getId(), car);
        return "Car succesfully saved";
    }

    public Car getCar(int id) {
        return (Car) hashOperations.get(key, id);
    }

    public ArrayList<Car> getCars() {
        return (ArrayList<Car>) hashOperations
                .keys(key)
                .stream()
                .map(k -> hashOperations.get(key, k))
                .collect(Collectors.toList());
    }

}
