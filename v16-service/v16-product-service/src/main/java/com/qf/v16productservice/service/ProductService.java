package com.qf.v16productservice.service;/*
@author:g
@Date:2019/8/5
    */

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.v16.api.IproductService;
import com.qf.v16.api.vo.productVO;
import com.qf.v16.common.base.BaseServiceimpl;
import com.qf.v16.common.base.IBaseDao;
import com.qf.v16.entity.TProduct;
import com.qf.v16.entity.TProductDesc;
import com.qf.v16.mapper.TProductDescMapper;
import com.qf.v16.mapper.TProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class ProductService extends BaseServiceimpl<TProduct>  implements IproductService {

        @Autowired
        private TProductMapper tProductMapper;

        @Autowired
        private TProductDescMapper tProductDescMapper;
        @Override
        public IBaseDao<TProduct> getBaseDao() {

            return tProductMapper;
        }

        @Override
        public List<TProduct> list() {
                return tProductMapper.list();
        }

        @Override
        public PageInfo<TProduct> page(Integer pageIndex, Integer pageSize) {
                PageHelper.startPage(pageIndex,pageSize);
                List<TProduct> list=list();
                PageInfo<TProduct> pageInfo=new PageInfo<TProduct>(list,3);

                return pageInfo;
        }

        @Override
        @Transactional
        public int add(productVO vo) {
                System.err.println("进入这层..................................");
               TProduct product=vo.getProduct();
                tProductMapper.insertSelective(product);
                TProductDesc desc=new TProductDesc();
                desc.setProductId(product.getId());
                desc.setProductDesc(vo.getProductDesc());
                int count = tProductDescMapper.insertSelective(desc);
                return count;

        }

    @Override
    public int deleteByPrimaryKey(Long id) {
            TProduct product=new TProduct();
            product.setFlag(false);
            product.setId(id);
            product.setUpdateTime(new Date());
        return updateByPrimaryKeySelective(product);
    }

    /*
        @Override
        public List<TProduct> list() {
                System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                return tProductMapper.list();
        }*/
}
