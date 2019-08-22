package com.qf.springbootrabbitmq;

import com.qf.springbootrabbitmq.entity.ProductDto;
import com.qf.springbootrabbitmq.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void contextLoads() {
        ProductDto productDto=new ProductDto(1L,"从菜鸟到大佬");
        sender.send(productDto);
    }

}
