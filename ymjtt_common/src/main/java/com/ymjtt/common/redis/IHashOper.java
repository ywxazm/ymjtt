package com.ymjtt.common.redis;

import java.util.Map;
import java.util.Set;

/**
 * @auther ywx
 * @date 2019/3/7 9:30
 **/
public interface IHashOper extends IBaseRedisOper {

    String hget(String key, String field);

    Long hset(String key, String field, String value);

    //删除一个或者多个字段
    Long hdel(String key, String... field);

    //查看字段是否存在
    Boolean hexists(String key, String field);

    //获取key的所有字段
    Set<String> hkeys(String key);

    //获取key的所有字段和值
    Map<String, String> hgetAll(String key);

    //增加value值
    Long hincrBy(String key, String field, Long value);

    //增加value值, 浮点数
    Double hincrByFloat(String key, String field, Double value);
}
