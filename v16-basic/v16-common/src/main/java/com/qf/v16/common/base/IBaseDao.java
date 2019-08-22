package com.qf.v16.common.base;/*
@author:g
@Date:2019/8/5
    */

import java.util.List;

public interface IBaseDao<T>{
    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKeyWithBLOBs(T record);

    int updateByPrimaryKey(T record);

    List<T> list();
}
