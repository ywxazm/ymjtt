package com.ywx.ymjtt.common.util;

import org.junit.Test;

public class HttpClientUtilTest {

    @Test
    public void test01() {
        String url = "http://localhost:8080/manager_web/test01";
        String getResult = HttpClientUtil.doGet(url);
        String postResult = HttpClientUtil.doPost(url);
        System.out.println("getResult------>" + getResult);
        System.out.println("postResult------>" + postResult);
    }
}
