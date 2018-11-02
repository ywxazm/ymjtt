package com.ymjtt.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * @auther ywx
 * @date 2018/11/1 9:05
 * @info 将属性文件转换成DTO对象
 **/
@SuppressWarnings({"unchecked", "ConstantConditions"})
public class FileCfg2ObjUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileCfg2ObjUtil.class);

    private FileCfg2ObjUtil() {
    }

    /**
     * @info 将*.properties文件转Object
     * @param fileName  文件名
     * @param clazz     对应对象
     * @return  Object
     */
    public static Object getObj(String fileName, Class clazz) {
        Object obj = new Object();
        try {
            String filePath = Thread.currentThread().getContextClassLoader().getResource(fileName)
                    .toString().replaceAll("\\\\", "/")
                    .replace("file:/", "");

            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(filePath)));
            Enumeration<?> enumeration = properties.propertyNames();

            HashMap<String, Object> dtoMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                dtoMap.put(key, properties.getProperty(key));
            }

            ObjectMapper mapper = new ObjectMapper();
            String JsonObj = mapper.writeValueAsString(dtoMap);
            obj = mapper.readValue(JsonObj, clazz);
        } catch (IOException ex) {
            logger.error("the thread {}, the error msg {}", Thread.currentThread().getName(), ex.getMessage());
        }
        return obj;
    }
}
