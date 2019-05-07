package com.ymjtt.common.util.objPackage;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/29 9:11
 **/
@Component
public class BeanUtil<T> {

    /**
     * request封装成javabean
     * @author  ywx
     * @date    2019/2/2 14:44
     * @param   [request, t]
     * @return  T
     */
    public T buildObj(HttpServletRequest request, T t) throws InvocationTargetException, IllegalAccessException {
        BeanUtils.populate(t, request.getParameterMap());
        return t;
    }

    /**
     * map 转 javabean
     * @author  ywx
     * @date    2019/2/2 14:44
     * @param   [map, obj]
     * @return  void
     */
    public static void Map2Bean(Map<String, Object> map, Object obj) throws InvocationTargetException, IllegalAccessException {
        if (map == null || obj == null) {
            return;
        }
        BeanUtils.populate(obj, map);
    }

    /**
     * javabean 转 Map
     * @author  ywx
     * @date    2019/2/2 14:45
     * @param   [obj]
     * @return  java.util.Map<java.lang.String,java.lang.Object>
     */
    public static Map<String, Object> bean2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (obj == null) {
                return null;
            }
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);

                    map.put(key, value);
                }
            }
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return map;
    }
}
