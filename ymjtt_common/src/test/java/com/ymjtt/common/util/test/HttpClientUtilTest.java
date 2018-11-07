package com.ymjtt.common.util.test;

import com.ymjtt.common.httpclient.HttpClientUtil;
import org.junit.Test;

public class HttpClientUtilTest {

    @Test
    public void test01() {
        String url = "http://www.baidu.com";
        String getResult = HttpClientUtil.doGet(url);
        String postResult = HttpClientUtil.doPost(url);
        System.out.println("getResult------>" + getResult);
        System.out.println("postResult------>" + postResult);
    }
}
