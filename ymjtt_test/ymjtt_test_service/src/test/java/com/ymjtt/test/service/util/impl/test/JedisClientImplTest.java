package com.ymjtt.test.service.util.impl.test;

import com.ymjtt.test.service.util.JedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Redis测试
 * @author  ywx
 * @date    2018/11/14 23:17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class JedisClientImplTest {

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void test01() {
        jedisClient.set("a", "abc123");
        String a = jedisClient.get("a");
        System.out.println(a);
    }
}
