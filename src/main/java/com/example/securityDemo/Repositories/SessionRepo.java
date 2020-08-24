package com.example.securityDemo.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisIndexedSessionRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SessionRepo {

    @Autowired
    private RedisIndexedSessionRepository repository;

    public Map<String, String> findById(String id) {
        Session session = (Session) repository.findById(id);
        Map<String, String> map = new HashMap<>();
        map.put("Attribute names: ", session.getAttributeNames().toString());
        map.put("user: ", session.getAttribute("user").toString());
        map.put("ID: ", session.getId());
        map.put("Creation time: ", session.getCreationTime().toString());
        map.put("Last accessed time: ", session.getLastAccessedTime().toString());
        map.put("Max inactive time: ", session.getMaxInactiveInterval().toString());
        return map;
    }

}
