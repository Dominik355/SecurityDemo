package com.example.securityDemo.Repositories;

import com.example.securityDemo.Models.RedisModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class RedisRepo {

    @Autowired
    private HashOperations hashOperations;

    private final String key = "RM";

    public String saveModel(RedisModel redisModel) {
        hashOperations.put(key, redisModel.getId(), redisModel );
        return "Model succesfully saved";
    }

    public RedisModel getModel(int id) {
        return (RedisModel) hashOperations.get(key, id);
    }

    public ArrayList<RedisModel> getModels() {
        return (ArrayList<RedisModel>) hashOperations
                .keys(key)
                .stream()
                .map(k -> hashOperations.get(key, k))
                .collect(Collectors.toList());
    }

}
