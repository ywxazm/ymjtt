package com.ymjtt.test.web.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.okhttp3.OkHttp3Callback;
import com.ymjtt.common.okhttp3.UserDTO;
import com.ymjtt.common.util.JSONConvertUtil;
import com.ymjtt.common.okhttp3.OkHttp3Util;
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
@SuppressWarnings("ALL")
public class OkHttp3UtilTest {

    /**
     * 同步,get请求, 无参
     */
    @Test
    public void test01() throws IOException {
        Response response = OkHttp3Util.getSyn("http://localhost:9001/test_web/forOKHttp3Test00");
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 同步,get请求, 有参
     */
    @Test
    public void test02() throws IOException {
        Response response = OkHttp3Util.getSyn("http://localhost:9001/test_web/forOKHttp3Test00?name=小明&age=21");
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 同步,POST请求, 无参
     */
    @Test
    public void test03() throws IOException {
        Response response = OkHttp3Util.postSyn("http://localhost:9001/test_web/forOKHttp3Test01");
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 同步,POST请求, map参数
     */
    @Test
    public void test04() throws IOException {
        HashMap map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "21");
        Response response = OkHttp3Util.postSyn("http://localhost:9001/test_web/forOKHttp3Test01", map);
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 同步,POST请求, json参数(contentType为application/x-www-form-urlencoded;charset=utf8, 那么它request中的参数还是以: name1=value1&name2=value2  的方式提交)
     */
    @Test
    public void test05() throws IOException {
        HashMap map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "21");
        Response response = OkHttp3Util.postSyn("http://localhost:9001/test_web/forOKHttp3Test01", JSONConvertUtil.map2Json(map));
        System.out.println(response);
        System.out.println(response.body().string());
    }




    /**
     * 同步,POST请求, json参数(contentType为application/json;charset=utf8, 那么它request中的参数还是以: {key1:value1,key2:value2}  的方式提交)
     */
    @Test
    public void test06() throws IOException {
        HashMap map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "21");
        Response response = OkHttp3Util.postSyn("http://localhost:9001/test_web/forOKHttp3Test02", JSONConvertUtil.map2Json(map));
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 同步,POST请求, json参数(contentType为application/json;charset=utf8, 那么它request中的参数直接以: {key1:value1,key2:value2} 格式提交)
     */
    @Test
    public void test07() throws IOException {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(12);
        userDTO.setName("小明");
        userDTO.setAge(88);
        Response response = OkHttp3Util.postSynAppointContextType("http://localhost:9001/test_web/forOKHttp3Test0", JSONConvertUtil.obj2Json(userDTO), "application/json;charset=utf-8");
        System.out.println(response);
        System.out.println(response.body().string());
    }

    /**
     * 异步,post请求,Map参数
     */
    @Test
    public void test() throws JsonProcessingException, InterruptedException {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("name", "小明");
        OkHttp3Util.postAsyn("http://localhost:9001/test_web/forOKHttp3Test00", paramMap, new OkHttp3Callback() {
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
