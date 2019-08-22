package com.qf.v16searchservice;

import com.qf.api.ISearchService;
import com.qf.v16.entity.TProduct;
import com.qf.v16.pojo.ResultBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16SearchServiceApplicationTests {

    @Autowired
    private ISearchService searchService;
    @Test
    public void solrTest() {
        ResultBean resultBean=searchService.synAllDate();
        System.out.println(resultBean.getData());
    }
    @Test
    public void queryTest(){
        ResultBean resultBean = searchService.queryByKeywords("锋聊");

      List<TProduct> list= (List<TProduct>) resultBean.getData();

       for(TProduct tProduct:list){
            System.out.println(tProduct.getName());
        }

    }

}
