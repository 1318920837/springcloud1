package com.qf.v16userservice.service;/*
@author:g
@Date:2019/8/15
    */

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.service.IUserServcie;
import com.qf.v16.common.base.BaseServiceimpl;
import com.qf.v16.common.base.IBaseDao;
import com.qf.v16.entity.TUser;
import com.qf.v16.mapper.TUserMapper;
import com.qf.v16.pojo.ResultBean;
import com.qf.v16userservice.until.JWTUtil;
import io.jsonwebtoken.Claims;

import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends BaseServiceimpl<TUser> implements IUserServcie {
    @Autowired
    private TUserMapper tUserMapper;

    @Resource(name = "redisTemplate4String")
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResultBean checkLogin(TUser user) {
        String username=user.getUsername();
        TUser currentUser =tUserMapper.checkLogin(username);
       /* System.out.println(user.getPassword());
        System.out.println("------------------------------++++++++++++++++++++++++++++");
*/

       if(currentUser!=null) {
           if (user.getPassword().equals(currentUser.getPassword())) {
               JWTUtil jwtUtil=new JWTUtil();
               jwtUtil.setTtl(30*60*1000);
               jwtUtil.setSecretKey("java1904");
               String jwtToken = jwtUtil.createJwtToken(currentUser.getId().toString(), currentUser.getUsername());

               return new ResultBean("200",jwtToken);
           }
       }

        return new ResultBean("404","账号密码错误");
    }



    /*@Override
    public ResultBean checkIsLogin(String uuid) {
        StringBuilder key=new StringBuilder("usertoken:").append(uuid);
        TUser current = (TUser) redisTemplate.opsForValue().get(key.toString());
        if(current!=null){
            redisTemplate.expire(key.toString(),30,TimeUnit.MINUTES);
            return new ResultBean("200",uuid);
        }

        return new ResultBean("404","当前用户未登录");
    }*/
    @Override
    public ResultBean checkIsLogin(String jwtToken) {
       JWTUtil jwtUtil=new JWTUtil();
       jwtUtil.setSecretKey("java1904");
       try{
           Claims claims=jwtUtil.parseJwtToken(jwtToken);
           String username=claims.getSubject();
           return new ResultBean("200",jwtToken);
       }catch (SignatureException e){
           return new ResultBean("404","当前用户未登录");
       }

    }
    @Override
    public ResultBean loginout(String uuid) {
        StringBuilder key=new StringBuilder("usertoken:").append(uuid);
        redisTemplate.delete(key.toString());

        return new ResultBean("200","已删除凭证");
    }

    @Override
    public IBaseDao<TUser> getBaseDao() {
        return null;
    }
}
