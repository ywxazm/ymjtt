package com.ywx.ymjtt.web.test;

import com.ywx.ymjtt.common.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springmvc.xml"})
public class WebTest {

    @Test
    public void test01() {
        String url = "http://localhost:8080/manager_web/test01";
        String getResult = HttpClientUtil.doGet(url);
        String postResult = HttpClientUtil.doPost(url);
        System.out.println("getResult------>" + getResult);
        System.out.println("postResult------>" + postResult);
    }
}
