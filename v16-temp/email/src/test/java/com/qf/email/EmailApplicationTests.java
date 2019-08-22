package com.qf.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailApplicationTests {
    @Autowired
    private JavaMailSender javaMailSender;
    @Test
    public void contextLoads() {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("13387902138@163.com");
        simpleMailMessage.setTo("1318920837@qq.com");
        simpleMailMessage.setSubject("很棒的交易");
        simpleMailMessage.setText("快给钱");

        javaMailSender.send(simpleMailMessage);
    }

}
