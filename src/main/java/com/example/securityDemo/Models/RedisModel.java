package com.example.securityDemo.Models;


import org.springframework.data.redis.core.RedisHash;
import javax.persistence.Id;
import java.io.Serializable;

public class RedisModel implements Serializable {

    @Id
    private Integer id;
    private String name;
    private Integer age;
    private String address;

    public RedisModel() {
    }

    public RedisModel(Integer id, String name, Integer age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
