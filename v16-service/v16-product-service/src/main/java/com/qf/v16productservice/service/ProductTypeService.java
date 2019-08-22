package com.qf.v16productservice.service;/*
@author:g
@Date:2019/8/8
    */

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.v16.api.IProductPetyService;
import com.qf.v16.api.IproductService;
import com.qf.v16.common.base.BaseServiceimpl;
import com.qf.v16.common.base.IBaseDao;
import com.qf.v16.common.base.IBaseService;
import com.qf.v16.entity.TProduct;
import com.qf.v16.entity.TProductType;
import com.qf.v16.mapper.TProductTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Service
public class ProductTypeService extends BaseServiceimpl<TProductType> implements IProductPetyService {

    @Autowired
    private TProductTypeMapper tProductTypeMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public IBaseDao<TProductType> getBaseDao() {
        return tProductTypeMapper;
    }

    @Override
    public List<TProductType> list() {

        String key="TProductType:list";
        List<TProductType> list = (List<TProductType>) redisTemplate.opsForValue().get(key);
        if(list!=null){
            return  list;
        }
        System.out.println("没有数据");
        List<TProductType> list1=super.list();
        if(list1!=null){
            redisTemplate.opsForValue().set(key,list1);
        }
        return list1;
    }
}
