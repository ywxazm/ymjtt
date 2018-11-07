package com.ymjtt.test.web.test;

import com.ymjtt.common.httpclient.HttpClientUtil;
import org.junit.Test;

public class WebControllerTest {

    @Test
    public void test01() {
        String url = "http://localhost:9001/test_web//test01";
        String getResult = HttpClientUtil.doGet(url);
        String postResult = HttpClientUtil.doPost(url);
        System.out.println("getResult------>" + getResult);
        System.out.println("postResult------>" + postResult);
    }
}
