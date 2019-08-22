package com.qf.v16.common.base;/*
@author:g
@Date:2019/8/5
    */

import java.util.List;

public abstract class BaseServiceimpl<T> implements IBaseService<T> {

    public abstract IBaseDao<T> getBaseDao();

    public int deleteByPrimaryKey(Long id) {
        return getBaseDao().deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return getBaseDao().insert(record);
    }

    public int insertSelective(T record) {
        return getBaseDao().insertSelective(record);
    }

    public T selectByPrimaryKey(Long id) {
        return getBaseDao().selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getBaseDao().updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(T record) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(record);
    }

    public int updateByPrimaryKey(T record) {
        return getBaseDao().updateByPrimaryKeyWithBLOBs(record);
    }

    public List<T> list() {
        return getBaseDao().list();
    }
}
