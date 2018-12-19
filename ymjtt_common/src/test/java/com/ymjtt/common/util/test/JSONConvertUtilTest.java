package com.ymjtt.common.util.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.util.json.JSONConvertUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @auther ywx
 * @date 2018/11/5 14:37
 * @info
 **/
public class JSONConvertUtilTest {

    /**
     * map --> json
     */
    @Test
    public void test01() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "19");
        String json = JSONConvertUtil.map2Json(map);
        System.out.println(json);
    }

    /**
     * json --> map
     */
    @Test
    public void test02() throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", "19");
        String json = JSONConvertUtil.map2Json(map);
        Map<String, String> map2 = JSONConvertUtil.json2map(json);
        Set<String> set = map2.keySet();
        for (String key : set) {
            System.out.println(key + " : " + map.get(key));
        }
    }
}
