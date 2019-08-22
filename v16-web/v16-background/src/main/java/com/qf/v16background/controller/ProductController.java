package com.qf.v16background.controller;/*
@author:g
@Date:2019/8/6
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.qf.api.ISearchService;
import com.qf.v16.api.IproductService;
import com.qf.v16.api.vo.productVO;
import com.qf.v16.common.constant.RabbitMQContent;
import com.qf.v16.entity.TProduct;
import com.qf.v16.pojo.ResultBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    private IproductService iproductService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("getById/{id}")
    @ResponseBody
    public TProduct getById(@PathVariable Long id) {
        return iproductService.selectByPrimaryKey(id);
    }

    @RequestMapping("list")
    public String list(Model model) {
        List<TProduct> list = iproductService.list();
        model.addAttribute("list", list);
        return "product/list";
    }

    @RequestMapping("page/{pageIndex}/{pageSize}")
    public String page(Model model, @PathVariable("pageIndex") Integer pageIndex, @PathVariable Integer pageSize) {
        PageInfo<TProduct> pageInfo = iproductService.page(pageIndex, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "product/list";
    }

    @RequestMapping("add")
    public String add(productVO vo) {
        int count = iproductService.add(vo);

        //iSearchService.updateById()

       /* if(count>0){
            return "redirect:/product/page/1/1";
        }*/
        rabbitTemplate.convertAndSend(RabbitMQContent.BACKGROUND_PRODUCT_EXCHANGE, "product.add", count);
        return null;
    }

    @RequestMapping("delById/{id}")
    @ResponseBody
    public ResultBean delById(@PathVariable Long id) {
        int count = iproductService.deleteByPrimaryKey(id);
        if (count > 0) {
            return new ResultBean("200", "删除成功");
        }
        return new ResultBean("200", "删除失败!你的操作有误");

    }
}
