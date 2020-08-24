package com.example.securityDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

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

    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object>  template = new RedisTemplate<>();
        template.setConnectionFactory(myLettuceConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(myLettuceConnectionFactory());
        return template;
    }

    @Bean
    public HashOperations hashOperations(){
        return this.stringRedisTemplate().opsForHash();
    }

    @Bean
    public ListOperations listOperations() {
        return this.redisTemplate().opsForList();
    }

}
