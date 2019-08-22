package com.qf.v16userservice;


import com.qf.service.IUserServcie;
import com.qf.v16.entity.TUser;
import com.qf.v16.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16UserServiceApplicationTests {

    @Autowired
    private IUserServcie userServcie;
    @Test
    public void contextLoads() {
        TUser user=new TUser();
        user.setUsername("java1904");
        user.setPassword("123");
        ResultBean resultBean=userServcie.checkLogin(user);
        System.out.println(resultBean.getData());
    }

}
