package com.ymjtt.common.util.file;

import com.ymjtt.common.util.json.JSONConvertUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @auther ywx
 * @date 2018/11/1 9:05
 * 将属性文件转换成对象
 **/
public class PropertiesCfg2ObjUtil {

    private PropertiesCfg2ObjUtil() {
    }

    /**
     * 将*.properties文件转POJO
     *
     * @param fileName 文件名
     * @return Object
     */
    public static Object getObjFromPropertiesFile(String fileName, Class<?> clazz) throws IOException {
        Map<String, String> dtoMap = getMapFromPropertiesFile(fileName);
        String JsonObj = JSONConvertUtil.map2Json(dtoMap);
        return JSONConvertUtil.json2Obj(JsonObj, clazz);
    }

    /**
     * 将*.properties文件转Map
     *
     * @param fileName 文件名
     * @return Map<String, String>
     */
    public static Map<String, String> getMapFromPropertiesFile(String fileName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        String filePath;
        if (null != url) {
            filePath = url.toString().replaceAll("\\\\", "/").replace("file:/", "");
        }else {
            throw new FileNotFoundException();
        }

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(filePath)));
        Enumeration<?> enumeration = properties.propertyNames();

        Map<String, String>dtoMap = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            dtoMap.put(key, properties.getProperty(key));
        }
        return dtoMap;
    }
}
