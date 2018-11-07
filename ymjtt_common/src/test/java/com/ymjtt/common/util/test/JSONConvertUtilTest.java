package com.ymjtt.common.util.test;

import com.ymjtt.common.okhttp3.OkHttp3DTO;
import com.ymjtt.common.util.JSONConvertUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @auther ywx
 * @date 2018/11/5 14:37
 * @info
 **/
@SuppressWarnings("ALL")
public class JSONConvertUtilTest {

    /**
     * map --> json
     */
    @Test
    public void test01() {
        HashMap map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "19");
        String json = JSONConvertUtil.map2Json(map);
        System.out.println(json);
    }

    /**
     * json --> map
     */
    @Test
    public void test02() {
        Map map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "19");
        String json = JSONConvertUtil.map2Json(map);
        Map map2 = JSONConvertUtil.json2map(json);
        Set<String> set = map2.keySet();
        for (String key : set) {
            System.out.println(key + " : " + map.get(key));
        }

    }
    /**
     * obj --> json
     */
    @Test
    public void test03() {
        OkHttp3DTO okHttp3DTO = new OkHttp3DTO();
        okHttp3DTO.setBytesSize(1);
        okHttp3DTO.setConnectTimeout(2);
        okHttp3DTO.setReadTimeout(3);
        okHttp3DTO.setRetryCount(4);
        okHttp3DTO.setWriteTimeout(5);
        String json = JSONConvertUtil.obj2Json(okHttp3DTO);
        System.out.println(json);
    }
    /**
     * json --> obj
     */
    @Test
    public void test04() {
        OkHttp3DTO okHttp3DTO = new OkHttp3DTO();
        okHttp3DTO.setBytesSize(1);
        okHttp3DTO.setConnectTimeout(2);
        okHttp3DTO.setReadTimeout(3);
        okHttp3DTO.setRetryCount(4);
        okHttp3DTO.setWriteTimeout(5);
        String json = JSONConvertUtil.obj2Json(okHttp3DTO);

        OkHttp3DTO okHttp3DTO1 = (OkHttp3DTO)JSONConvertUtil.json2Obj(json, OkHttp3DTO.class);
        System.out.println(okHttp3DTO1);
    }
}
