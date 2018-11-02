package com.ymjtt.common.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymjtt.common.callback.OkHttp3Callback;
import com.ymjtt.common.util.OkHttp3Util;
import okhttp3.Call;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/1 14:50
 **/
public class OkHttp3UtilTest {

    /**
     * 同步,get请求
     */
    @Test
    public void test01() {
        OkHttp3Util okHttp3Util = new OkHttp3Util();
        Response response = okHttp3Util.getSyn("http://www.baidu.com");
        System.out.println(response);
    }

    /**
     * 同步,post请求,无参
     */
    @Test
    public void test02() {
        OkHttp3Util okHttp3Util = new OkHttp3Util();
        Response response = okHttp3Util.postSyn("http://www.baidu.com");
        System.out.println(response);
    }
    /**
     * 同步,post请求,json参数
     */
    @Test
    public void test03() {
        OkHttp3Util okHttp3Util = new OkHttp3Util();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("中", "国");
        Response response = okHttp3Util.postSyn("http://www.baidu.com", paramMap);
        System.out.println(response);
    }
    /**
     * 同步,post请求,Map参数
     */
    @Test
    public void test04() throws JsonProcessingException {
        OkHttp3Util okHttp3Util = new OkHttp3Util();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("中", "国");
        ObjectMapper objectMapper =new ObjectMapper();
        Response response = okHttp3Util.postSyn("http://www.baidu.com", objectMapper.writeValueAsString(paramMap));
        System.out.println(response);
    }

    /**
     * 异步,post请求,Map参数
     */
    @Test
    public void test05() throws JsonProcessingException, InterruptedException {
        OkHttp3Util okHttp3Util = new OkHttp3Util();
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("中", "国");
        ObjectMapper objectMapper =new ObjectMapper();
        okHttp3Util.postAsyn("http://localhost:9001/test_web/test01", paramMap, new OkHttp3Callback() {
            @Override
            public void failed(Call call, IOException e) {
                System.out.println("fail method 被回调");
            }
            @Override
            public void success(Call call, Response response) throws IOException {
                System.out.println("success method 被回调");
                System.out.println(response.body().string());
            }
        });
    }
}
