package com.qf.springbootredis.config;/*
@author:g
@Date:2019/8/14
    */

import io.lettuce.core.support.RedisClientFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisTest {

    @Bean(name ="aRedisTemplate1")
    public RedisTemplate<String,String> getRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,String> redisTemplate=new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
