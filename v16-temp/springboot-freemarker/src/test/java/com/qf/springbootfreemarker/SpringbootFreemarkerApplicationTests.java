package com.qf.springbootfreemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootFreemarkerApplicationTests {
    @Autowired
    private Configuration configuration;

    @Test
    public void contextLoads() throws IOException, TemplateException {
        Template template = configuration.getTemplate("hello.ftl");

        product product=new product();
        product.setId(1L);
        product.setName("aaaaaa");
        product.setPrice(4000L);
        Map<String,Object> date=new HashMap<>();

        date.put("product",product);
        date.put("username","java");
        FileWriter out=new FileWriter("D:\\idea第三阶段代码\\v16\\v16-temp\\springboot-freemarker\\src\\main\\resources\\templates\\hello.html");
        template.process(date,out);
        System.out.println("success");

    }



}
