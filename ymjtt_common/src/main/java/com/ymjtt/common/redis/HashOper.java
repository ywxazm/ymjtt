package com.ymjtt.common.redis;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @auther ywx
 * @date 2019/3/7 9:48
 **/
@Component
public class HashOper extends AbstractBaseRedisOper implements IHashOper {

    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    public Long hset(String key, String field, String value) {
        return jedisCluster.hset(key, field, value);
    }

    //删除一个或者多个字段
    public Long hdel(String key, String... field) {
        return jedisCluster.hdel(key, field);
    }

    //查看字段是否存在
    public Boolean hexists(String key, String field) {
        return jedisCluster.hexists(key, field);
    }

    //获取key的所有字段
    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    //获取key的所有字段和值
    public Map<String, String> hgetAll(String key) {
        return jedisCluster.hgetAll(key);
    }

    //增加value值
    public Long hincrBy(String key, String field, Long value) {
        return jedisCluster.hincrBy(key, field, value);
    }

    //增加value值, 浮点数
    public Double hincrByFloat(String key, String field, Double value) {
        return jedisCluster.hincrByFloat(key, field, value);
    }
}
