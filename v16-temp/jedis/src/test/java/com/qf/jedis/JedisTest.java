package com.qf.jedis;/*
@author:g
@Date:2019/8/14
    */

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    @Test
    public void jedisT(){
        Jedis jedis=new Jedis("120.27.243.183",6379);

        jedis.auth("java1904");
        jedis.set("target","很棒");
        String target = jedis.get("target");
        System.out.println(target);
    }
    @Test
    public void HashTest(){
        Jedis jedis=new Jedis("120.27.243.183",6379);

        jedis.auth("java1904");
        Map<String,String> map=new HashMap<String, String>();
        map.put("name","钢铁是怎么炼成的");
        map.put("price","1111");
        jedis.hmset("book:1",map);
        Map<String, String> map1 = jedis.hgetAll("book:1");
        Set<Map.Entry<String, String>> entrySe = map1.entrySet();
        for (Map.Entry<String, String> stringStringEntry : entrySe) {
            System.out.println(stringStringEntry.getKey());
        }

    }
}
