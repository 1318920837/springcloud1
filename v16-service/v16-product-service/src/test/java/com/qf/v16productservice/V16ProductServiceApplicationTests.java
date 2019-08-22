package com.qf.v16productservice;

import com.qf.v16.api.IProductPetyService;
import com.qf.v16.api.IproductService;
import com.qf.v16.entity.TProduct;
import com.qf.v16.entity.TProductType;
import com.qf.v16.mapper.TProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.qf.v16.mapper")
public class V16ProductServiceApplicationTests {

    @Autowired
    private IproductService iproductServices;

    @Autowired
    private IProductPetyService petyService;
    @Autowired
    private DataSource dataSource;
    @Test
    public void contextLoads() {
        TProduct tProduct = iproductServices.selectByPrimaryKey(1L);
        System.out.println(tProduct.getName());
    }
    @Test
    public void Testcontext() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
    @Test
    public  void TestList(){
        List<TProductType> list = petyService.list();
        for(TProductType t:list){
            System.out.println(t.getName());
        }
    }
}
