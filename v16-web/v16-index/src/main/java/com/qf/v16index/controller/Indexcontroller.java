package com.qf.v16index.controller;/*
@author:g
@Date:2019/8/8
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.v16.api.IProductPetyService;
import com.qf.v16.entity.TProductType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("index")
public class Indexcontroller {


    @Reference
    private IProductPetyService petyService;

    @RequestMapping("home")
    public String home(Model model) {
        List<TProductType> list = petyService.list();
        model.addAttribute("list", list);
        return "home";
    }
}
