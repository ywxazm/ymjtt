package com.ymjtt.manager.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther ywx
 * @date 2019/3/7 10:17
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class GenerateIdTest {

    @Autowired
    private GenerateId generateId;

    @Test
    public void test01() {
        for(int i = 1; i < 100; i++) {
            String s1 = generateId.generateProductBarCode();
            System.out.println(s1);
            String s2 = generateId.generateProductCatId();
            System.out.println(s2);
            String s3 = generateId.generateProductID();
            System.out.println(s3);
        }
    }
}
