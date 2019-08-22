package com.qf.springbootrabbitmq.entity;/*
@author:g
@Date:2019/8/13
    */

import javax.swing.*;
import java.io.Serializable;

public class ProductDto implements Serializable {
    private Long id;
    private String name;


    public ProductDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductDto() {
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
}
