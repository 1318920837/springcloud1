package com.qf.v16search.Controller;/*
@author:g
@Date:2019/8/10
    */


import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.api.ISearchService;
import com.qf.v16.pojo.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("controller")
public class SearchController {

    @Reference
    private ISearchService searchService;

    @RequestMapping("search")
    public String search(String keywords, Model model) {
        ResultBean resultBean = searchService.queryByKeywords(keywords);
        model.addAttribute("resultBean", resultBean);
        return "list";
    }
}
