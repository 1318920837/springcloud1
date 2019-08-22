package com.qf.v16item.config;/*
@author:g
@Date:2019/8/12
    */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class configCommon {

    @Bean
    public ThreadPoolExecutor getPool() {
        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(processors, processors * 2, 30L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(100));

        return pool;
    }
}
