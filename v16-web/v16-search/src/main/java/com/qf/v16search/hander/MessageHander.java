package com.qf.v16search.hander;/*
@author:g
@Date:2019/8/13
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.ISearchService;
import com.qf.v16.common.constant.RabbitMQContent;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHander {

    @Reference
    private ISearchService iSearchService;

    @RabbitListener(queues = RabbitMQContent.ADDOEUPDATE_SEARCH_QUEUE)
    @RabbitHandler
    public void processAdd(Long id) {
        iSearchService.updateById(id);
    }
}
