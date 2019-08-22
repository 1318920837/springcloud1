package com.qf.v16cartservice.PoJo;/*
@author:g
@Date:2019/8/17
    */

import java.io.Serializable;
import java.util.Date;

public class CartItem implements Serializable,Comparable<CartItem>{
    private Long productId;
    private Integer count;
    private Date updatetime;

    public CartItem(Long productId, Integer count, Date updatetime) {
        this.productId = productId;
        this.count = count;
        this.updatetime = updatetime;
    }

    public CartItem() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + productId +
                ", count=" + count +
                ", updatetime=" + updatetime +
                '}';
    }

    @Override
    public int compareTo(CartItem o) {
        return (int) (o.getUpdatetime().getTime()-this.getUpdatetime().getTime());
    }
}
