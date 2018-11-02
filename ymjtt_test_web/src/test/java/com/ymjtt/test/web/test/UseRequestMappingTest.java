package com.ymjtt.test.web.test;

import com.ymjtt.common.util.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UseRequestMappingTest {

    private static final String url = "http://localhost:9001/test_web";
    private static Map<String, String> queryMap = new HashMap<>();

    /**
     * @RequestMapping 路径的测试
     */
    @Test
    public void test01() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping01");
        System.out.println("/useRequestMapping01" + response);
    }
    @Test
    public void test02() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping02");
        System.out.println("/useRequestMapping02" + response);
    }
    @Test
    public void test03() {
        String response01 = HttpClientUtil.doPost(url + "/useRequestMapping031");
        String response02 = HttpClientUtil.doPost(url + "/useRequestMapping032");
        System.out.println("/useRequestMapping031" + response01);
        System.out.println("/useRequestMapping032" + response02);
    }
    @Test
    public void test04() {
        String response01 = HttpClientUtil.doPost(url + "/useRequestMapping041");
        String response02 = HttpClientUtil.doPost(url + "/useRequestMapping042");
        System.out.println("/useRequestMapping041" + response01);
        System.out.println("/useRequestMapping042" + response02);
    }
    @Test
    public void test05() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping05/abc");
        System.out.println("/useRequestMapping05" + response);
    }
    @Test
    public void test06() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping06/123aa");
        System.out.println("/useRequestMapping06" + response);
    }
    @Test
    public void test07() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping07/123aa");
        System.out.println("/useRequestMapping07" + response);
    }
    @Test
    public void test08() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping08/123aa");
        System.out.println("/useRequestMapping08" + response);
    }

    /**
     * @RequestMapping 指定方法的测试
     */
    @Test
    public void test10() {
        String get = HttpClientUtil.doGet(url + "/useRequestMapping10");
        String post = HttpClientUtil.doPost(url + "/useRequestMapping10");
        System.out.println("/useRequestMapping10" + get);
        System.out.println("/useRequestMapping10" + post);          //服务器指定请求方式与实际请求方式不符，会导致请求不成功
    }
    @Test
    public void test11() {
        String response = HttpClientUtil.doPost(url + "/useRequestMapping11");
        System.out.println("/useRequestMapping11" + response);
    }



    /**
     * @RequestMapping 指定请求的content-Type字段，get请求没有此字段，故不考虑
     */
    @Test
    public void test12() {
        queryMap.put("name", "小明");
        String response = HttpClientUtil.doPost(url + "/useRequestMapping12", queryMap);
        System.out.println("/useRequestMapping12" + response);
    }


}
