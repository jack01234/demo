package com.example.demo;

import com.example.demo.dubbo.CifCoreConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    CifCoreConsumer cifCoreConsumer;
    @Test
    public void contextLoads() {
        cifCoreConsumer.queryMerchantInfo("8013932677");
    }
}
