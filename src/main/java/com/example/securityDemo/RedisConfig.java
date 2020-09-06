package com.example.securityDemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(myLettuceConnectionFactory());
        return template;
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new Converter<byte[], OffsetDateTime>() {

            @Override
            public OffsetDateTime convert(byte[] source) {
                return OffsetDateTime.parse(new String(source), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }
        }, new Converter<OffsetDateTime, byte[]>() {
            @Override
            public byte[] convert(OffsetDateTime source) {
                return source.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).getBytes();
            }
        }
                ));
    }

/*
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

*/
}
