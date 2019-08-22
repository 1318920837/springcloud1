package com.qf.v16cartservice.sevice;/*
@author:g
@Date:2019/8/17
    */

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.cart.cartapi.ICartService;
import com.qf.v16.pojo.ResultBean;
import com.qf.v16cartservice.PoJo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CartService implements ICartService {

    @Resource(name="redisTemplate4String")
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public ResultBean add(String key, Long productId, Integer count) {
        StringBuilder rediskey=new StringBuilder("user_cart:").append(key);
        Map<Object,Object> cart=redisTemplate.opsForHash().entries(rediskey.toString());
        if(cart.get(productId)!=null){
            CartItem cartItem= (CartItem) cart.get(productId);
            cartItem.setCount(cartItem.getCount()+count);
            cartItem.setUpdatetime(new Date());
            redisTemplate.opsForHash().putAll(rediskey.toString(),cart);
            return new ResultBean("200","添加成功");
        }
        CartItem cartItem=new CartItem(productId,count,new Date());
        cart.put(productId,cartItem);
        redisTemplate.opsForHash().putAll(rediskey.toString(),cart);
        return new ResultBean("200","添加成功");
    }

    @Override
    public ResultBean del(String key, Long productId) {
        StringBuilder rediskey=new StringBuilder("user_cart:").append(key);
        Long delete = redisTemplate.opsForHash().delete(rediskey.toString(), productId);
        if(delete>0){
            return new ResultBean("200","删除成功");
        }
        return new ResultBean("404","删除失败");
    }

    @Override
    public ResultBean update(String key, Long productId, Integer count) {
        StringBuilder rediskey=new StringBuilder("user_cart:").append(key);

            CartItem cartItem= (CartItem) redisTemplate.opsForHash().get(rediskey.toString(),productId);
            if(cartItem!=null) {
              cartItem.setCount(count);
              cartItem.setUpdatetime(new Date());
                redisTemplate.opsForHash().put(rediskey.toString(),productId,cartItem);
                return new ResultBean("200", "添加成功");
            }
        return new ResultBean("404", "不存在该商品，更新失败");
    }

    @Override
    public ResultBean query(String key) {
        StringBuilder rediskey=new StringBuilder("user_cart:").append(key);
        Map<Object,Object> cart=redisTemplate.opsForHash().entries(rediskey.toString());
        if(cart.size()>0) {
            Collection<Object> values = cart.values();
            TreeSet<CartItem> target=new TreeSet<>();

            for (Object value : values) {
                target.add((CartItem) value);
            }
            return new ResultBean("200",target);
        }
        return new ResultBean("400","数据库为空");
    }

    @Override
    public ResultBean parseTokenId(String token) {

       return new ResultBean();
    }
}
