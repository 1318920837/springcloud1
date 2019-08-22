package com.qf.v16cartservice;

import com.qf.cart.cartapi.ICartService;
import com.qf.v16.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16CartServiceApplicationTests {
    @Autowired
    private ICartService cartService;
    @Test
    public void contextLoads() {
        ResultBean resultBean=cartService.add("1234",1L,100);
        System.out.println(resultBean);
    }
    @Test
    public void del() {
        ResultBean resultBean=cartService.del("1234",1L);
        System.out.println(resultBean.getData());
    }
    @Test
    public void updaye() {
        ResultBean resultBean=cartService.update("1234",1L,100);
        System.out.println(resultBean.getData());
    }
    @Test
    public void query() {
        ResultBean query = cartService.query("1234");
        System.out.println(query.getData());
    }

}
