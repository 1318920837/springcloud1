package com.qf.springbootfastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SpringbootFastdfsApplicationTests {

    @Autowired
    private FastFileStorageClient client;
    @Test
    public void upload() throws FileNotFoundException {
        File file=new File("D:\\idea第三阶段代码\\v16\\v16-temp\\1.jpg");
        FileInputStream inputStream=new FileInputStream(file);
        StorePath sto = client.uploadImageAndCrtThumbImage(inputStream, file.length(), "jpg", null);
        System.out.println(sto.getFullPath());
        System.out.println(sto.getGroup());
        System.out.println(sto.getPath());
    }

}
