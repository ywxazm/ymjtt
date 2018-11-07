package com.ymjtt.test.web.test;

import com.ymjtt.common.okhttp3.OkHttp3Util;
import com.ymjtt.common.util.JSONConvertUtil;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/6 8:39
 **/
public class ReceiveDataTest {

    private static final String addr = "http://localhost:9001/test_web";

    @Test
    public void test01() throws IOException {
        Response get_response = OkHttp3Util.getSyn(addr + "/requestMapper01?id=2&name=小明&age=21");
        System.out.println("get: " + get_response);
        System.out.println("get: " + get_response.body().string());

        Map<String, String> map = new HashMap<>();
        map.put("id", "3");
        map.put("name", "小胆");
        map.put("age", "22");
        Response post_response = OkHttp3Util.postSyn(addr + "/requestMapper01", map);
        System.out.println("post: " + post_response);
        System.out.println("post: " + post_response.body().string());
    }
    @Test
    public void test02() throws IOException {
        Response get_response = OkHttp3Util.getSyn(addr + "/requestMapper02/2/小明/21");
        System.out.println("get: " + get_response);
        System.out.println("get: " + get_response.body().string());
    }
    @Test
    public void test03() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "3");
        map.put("name", "小胆");
        map.put("age", "22");
        Response get_response1 = OkHttp3Util.postSyn(addr + "/requestMapper030", map);
        System.out.println("post1: " + get_response1);
        System.out.println("post1: " + get_response1.body().string());

        Response get_response2 = OkHttp3Util.postSyn(addr + "/requestMapper031", map);
        System.out.println("post2: " + get_response2);
        System.out.println("post2: " + get_response2.body().string());
    }
    @Test
    public void test04() throws IOException {
        String[] array = {"aaa","bbb","ccc","ddd","eee"};
        Response get_response = OkHttp3Util.postSynAppointContextType(addr + "/requestMapper040", JSONConvertUtil.array2Json(array), "application/json;charset=utf-8");
        System.out.println("post: " + get_response);
        System.out.println("post: " + get_response.body().string());
    }
}
