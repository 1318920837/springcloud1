package com.qf.springbootfreemarker;/*
@author:g
@Date:2019/8/10
    */

public class product {
    private Long id;
    private String name;
    private Long price;


    public product(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
