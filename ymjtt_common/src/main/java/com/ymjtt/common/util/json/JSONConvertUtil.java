package com.ymjtt.common.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/5 9:57
 **/
public class JSONConvertUtil<T> {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * obj --> json 转换
     * @param pojo
     * @return
     * @throws JsonProcessingException
     */
    public static String obj2Json(Object pojo) throws JsonProcessingException {
        return mapper.writeValueAsString(pojo);
    }

    /**
     * json --> obj 转换
     * @param json
     * @return
     * @throws IOException
     */
    public static Object json2Obj(String json, Class<?> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    /**
     * map --> json 转换
     * @param map
     * @return
     */
    public static String map2Json(Map<String, Object> map) throws JsonProcessingException {
        return obj2Json(map);
    }

    /**
     * json --> map 转换
     * @param json
     * @return
     */
    public static Map<String, String> json2map(String json) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(Map.class, String.class, String.class);
        return mapper.readValue(json, javaType);
    }

    /**
     * list --> json 转换
     * @param list
     * @return
     */
    public static String list2Json(List<?> list) throws JsonProcessingException {
        return obj2Json(list);
    }

    /**
     * json --> list
     * @param json
     * @return
     */
    public static List<String> json2List(String json) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, String.class);
        return mapper.readValue(json, javaType);
    }

    /**
     * 泛型为任何对象, 都可转List
     * json --> list
     * @param json
     * @return
     */
    public List<T> json2List(String json, Class<T> clazz) throws IOException {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clazz);
        return mapper.readValue(json, javaType);
    }

    /**
     * array --> json 转换
     * @param array
     * @return
     */
    public static String array2Json(String[] array) throws JsonProcessingException {
        return obj2Json(array);
    }

    /**
     * json --> array
     * @param json
     * @return
     */
    public static String[] json2Array(String json) throws IOException {
        ArrayType arrayType = mapper.getTypeFactory().constructArrayType(String.class);
        return mapper.readValue(json, arrayType);
    }

}
