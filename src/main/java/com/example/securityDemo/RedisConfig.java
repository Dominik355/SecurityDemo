package com.example.securityDemo;

import com.example.securityDemo.Models.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.*;

@Configuration
public class RedisConfig {

    @Bean
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();;
        factory.setDatabase(0);
        return factory;
    }

    @Bean("myFactory")
    public LettuceConnectionFactory myLettuceConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory();;
        factory.setDatabase(1);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object>  template = new RedisTemplate<>();
        template.setConnectionFactory(myLettuceConnectionFactory());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        template.setKeySerializer(new GenericToStringSerializer<Object>(Object.class));
        template.afterPropertiesSet();
        return template;
    }


    @Bean
    public HashOperations hashOperations(){
        return this.redisTemplate().opsForHash();
    }

    @Bean
    public ListOperations listOperations() {
        return this.redisTemplate().opsForList();
    }

}
