package com.qf.v16item.Controller;/*
@author:g
@Date:2019/8/10
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.v16.api.IproductService;
import com.qf.v16.entity.TProduct;
import com.qf.v16.pojo.ResultBean;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Controller
@RequestMapping("item")
public class ItemController {

    @Reference
    private IproductService iproductService;

    @Autowired
    private Configuration configuration;

    @Autowired
    private ThreadPoolExecutor pool;

    @RequestMapping("creatById/{id}")
    @ResponseBody
    public ResultBean creatById(@PathVariable("id") Long id) {
        try {
            Template template = configuration.getTemplate("item.ftl");

            TProduct product = iproductService.selectByPrimaryKey(id);

            Map<String, Object> date = new HashMap<>();

            date.put("product", product);

            String path = ResourceUtils.getURL("classpath:static").getPath();


            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(path);

            stringBuilder.append(File.separator);

            stringBuilder.append(product.getId());

            stringBuilder.append(".html");

            String decode = URLDecoder.decode(stringBuilder.toString(), "utf-8");

            FileWriter out = new FileWriter(decode);

            template.process(date, out);
            return new ResultBean("200", "生成成功");
        } catch (IOException | TemplateException e) {
            e.printStackTrace();


        }

        return new ResultBean("500", "生成失败");

    }

    @RequestMapping("batchCreat")
    public ResultBean batchCreat(List<Long> ids) {

        List<Future<Long>> list = new ArrayList<>();
        for (Long id : ids) {
            Future<Long> future = pool.submit(new CreateTask(id));
            list.add(future);
        }

        List<Long> errors = new ArrayList<>();
        for (Future<Long> future : list) {
            try {
                Long resultNo = future.get();

                if (resultNo != 0) {
                    errors.add(resultNo);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        if (errors.size() > 0) {

            return new ResultBean("500", "批量处理也面失败");
        }
        return new ResultBean("200", "批量处理也面成功");
    }

    private class CreateTask implements Callable<Long> {
        private long id;

        public CreateTask(long id) {
            this.id = id;
        }


        @Override
        public Long call() throws Exception {

            try {
                Template template = configuration.getTemplate("item.ftl");

                TProduct product = iproductService.selectByPrimaryKey(id);

                Map<String, Object> date = new HashMap<>();

                date.put("product", product);

                String path = ResourceUtils.getURL("classpath:static").getPath();


                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append(path);

                stringBuilder.append(File.separator);

                stringBuilder.append(product.getId());

                stringBuilder.append(".html");

                String decode = URLDecoder.decode(stringBuilder.toString(), "utf-8");

                FileWriter out = new FileWriter(decode);

                template.process(date, out);
                return 0L;
            } catch (IOException | TemplateException e) {
                e.printStackTrace();


            }

            return id;

        }
    }

}


