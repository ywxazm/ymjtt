package com.ywx.ymjtt.web.test;

import com.ywx.ymjtt.common.utils.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author ywx
 * @Date 2018-10-29
 * 编码测试
 */
public class EnCodingTest {

    String url = "http://localhost:8080/manager_web";

    /***
     * 不开启任何编码设置，全部使用默认设定
     */
    @Test
    public void test10() {
        String requestMapping = "/test10";
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "小明");
        //get
        String str01 = HttpClientUtil.doGet(url + requestMapping, paramMap);        //服务器打印结果为：name:  å°æc，
        System.out.println(str01);        //此处返回数据为： å°æ????
        //post
        String str02 = HttpClientUtil.doPost(url + requestMapping, paramMap);        //服务器打印结果为：name:  ??，
        System.out.println(str02);        //此处返回数据为： ??????
    }

    /***
     * 解决服务器打印结果乱码问题
     * 产生原因：
     * 1.HttpClient请求数据在不传参的情况下，用utf-8进行编码；
     * 2.Tomcat用unicode进行解码，编码不一致，产生乱码
     * 解决方法：
     * 指定Tomcat解码所用字符集，在web.xml中配置
     */
    @Test
    public void test11() {
        String requestMapping = "/test10";
        String param = "?name=小明";
        String str = HttpClientUtil.doGet(url + requestMapping + param);        //服务器打印结果为：name:  å°æ，
        System.out.println(str);        //此处返回数据为： 小明????
    }
}
