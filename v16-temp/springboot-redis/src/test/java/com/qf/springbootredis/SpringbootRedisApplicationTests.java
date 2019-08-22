package com.qf.springbootredis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

   @Resource(name="aRedisTemplate1")
   private  RedisTemplate<String,String> redisTemplate;
    @Test
    public void contextLoads() {
        redisTemplate.opsForValue().set("moenry","1000");
        Object money = redisTemplate.opsForValue().get("moenry");
        System.out.println(money);
    }

}
