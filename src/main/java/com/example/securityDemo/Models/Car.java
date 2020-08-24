package com.example.securityDemo.Models;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Car")
public class Car implements Serializable {

    Integer id;
    String name;
    String type;

    public Car() {

    }

    public Car(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
