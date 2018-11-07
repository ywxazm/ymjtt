package com.ymjtt.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/5 9:57
 **/
public class JSONConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(JSONConvertUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * obj --> json 转换
     * @param pojo
     * @return
     * @throws JsonProcessingException
     */
    public static String obj2Json(Object pojo) {
        String result = null;
        try {
            result = mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException ex) {
            logger.error("the thread {}, catch pojo2Json error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return result;
    }

    /**
     * json --> obj 转换
     * @param json
     * @return
     * @throws IOException
     */
    public static Object json2Obj(String json, Class<?> clazz) {
        Object result = null;
        try {
            result = mapper.readValue(json, clazz);
        } catch (IOException ex) {
            logger.error("the thread {}, catch pojo2Json error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return result;
    }

    /**
     * map --> json 转换
     * @param map
     * @return
     */
    public static String map2Json(Map<String, String> map) {
        return obj2Json(map);
    }

    /**
     * json --> map 转换
     * @param json
     * @return
     */
    public static Map<String, String> json2map(String json) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class);
        Map<String, String> map = null;
        try {
            map = mapper.readValue(json, javaType);
        } catch (IOException ex) {
            logger.error("the thread {}, catch json2map error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return map;
    }

    /**
     * list --> json 转换
     * @param list
     * @return
     */
    public static String list2Json(List<?> list) {
        return obj2Json(list);
    }

    /**
     * json --> list
     * @param json
     * @return
     */
    public static List<String> json2List(String json) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, String.class);
        List<String> list = null;
        try {
            list = mapper.readValue(json, javaType);
        } catch (IOException ex) {
            logger.error("the thread {}, catch json2map error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return list;
    }

    /**
     * array --> json 转换
     * @param array
     * @return
     */
    public static String array2Json(String[] array) {
        return obj2Json(array);
    }

    /**
     * json --> array
     * @param json
     * @return
     */
    public static String[] json2Array(String json) {
        ArrayType arrayType = mapper.getTypeFactory().constructArrayType(String.class);
        String[] array = null;
        try {
            array = mapper.readValue(json, arrayType);
        } catch (IOException ex) {
            logger.error("the thread {}, catch json2map error {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return array;
    }

}
