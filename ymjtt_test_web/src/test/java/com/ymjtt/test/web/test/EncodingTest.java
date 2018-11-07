package com.ymjtt.test.web.test;

import com.ymjtt.common.httpclient.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/10/31 10:27
 **/
public class EncodingTest {

    private static final String url = "http://localhost:9001/test_web";

    private static Map<String, String> queryMap = new HashMap<>();

    /**
     * Get请求
     * 浏览器不做编码设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @Test
    public void encoding01() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doGet(url + "/encoding01", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:  name:  å°æ                  //浏览器编码用UrlEncoding("utf-8")的方式, 服务器解码用ISO-8859-1的方式
        //返回数据:    result ===>  å°æ, ????
    }
    /**
     * Get请求
     * 浏览器 get请求没有Content-type字段, 返回的数据中也没有Content-type字段, 服务器不会自动添加此字段; 浏览器指定用UrlEncoding("utf-8")进行编码. 服务器指定解码方式进行解码
     * 代码 添加返回头信息
     * 入参: name = "小明"
     */
    @Test
    public void encoding02() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doGet(url + "/encoding02", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:  tomcat封装数据name:  å°æ   自行编码解码处理的name:  小明     请求乱码处理完成
        //返回数据:      result ===>  ??, ????
    }
    /**
     * Get请求
     * 浏览器 在服务器代码中添加Content-Type字段
     * 代码 添加返回头信息
     * 入参: name = "小明"
     */
    @Test
    public void encoding03() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doGet(url + "/encoding03", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:  自行编码解码处理的name:  小明
        //返回数据:      result ===>  小明, 返回成功          已解决
    }
    /**
     * Get请求总结
     * 1. get请求及数据返回, 与 浏览器编码解码字符集/tomcat解码编码字符集有关
     * 2. 可通过修改UrlEncoding/tomcat的urlEncoding配置/response指定字符集解决乱码
     * */





    /**
     * Post请求
     * 浏览器不做编码设置
     * web.xml不做设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @Test
    public void encoding04() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doPost(url + "/encoding04", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:   name:  ??
        //返回数据:     result ===>  ??, ????
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码
     * web.xml不做设置
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @Test
    public void encoding05() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doPost(url + "/encoding05", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:   name:  小明            //此处看似没有问题了, 但没有明确指定tomcat的编码
        //返回数据:     result ===>  ??, ????
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码
     * web.xml指定Tomcat编码字符集
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @Test
    public void encoding06() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doPost(url + "/encoding06", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:   name:  小明            //结果与上面一致,说明Tomcat默认字符集为Utf8
        //返回数据:     result ===>  å°æ, è¿åæå
    }
    /**
     * Post请求
     * 浏览器 指定用utf8来进行编码, 解码
     * web.xml 指定Tomcat编码字符集
     * 代码不做编码处理
     * 入参: name = "小明"
     */
    @Test
    public void encoding07() {
        queryMap.put("name", "小明");
        String result = HttpClientUtil.doPost(url + "/encoding07", queryMap);
        System.out.println("result ===>  " + result);
        //服务器数据:   name:  小明
        //返回数据:     result ===>  小明, 返回成功           //此处虽然没有用代码指定Content-Type,但请求是有指定字符集的,所以返回也将与请求一致
    }
    /**
     * Post请求总结
     * 1. post请求及数据返回, 与 浏览器编码解码字符集/tomcat解码编码字符集有关
     * 2. 可通过修改浏览器编码方式/tomcat的encoding(Web.xml)指定字符集解决乱码
     * */

    /**
    * 最后,编码问题,就是 浏览器编码解码  和   Tomcat的编码解码   一致的问题
    * */
}
