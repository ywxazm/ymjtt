package com.ymjtt.common.util.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author  ywx
 * @date    2018/11/21 15:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class JedisClientTest {

    @Autowired
    private JedisClient jedisClient;

    @Test
   public void test() {
        jedisClient.set("a", "aaaaaaaaaa");
        jedisClient.set("b", "bbbbbbbbbb");

        System.out.println(jedisClient.get("a"));
        System.out.println(jedisClient.get("b"));
   }
}
