package com.ymjtt.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisCluster;

/**
 * @auther ywx
 * @date 2019/3/7 9:36
 **/
public abstract class AbstractBaseRedisOper implements IBaseRedisOper {

    //操作对象
    @Autowired
    protected JedisCluster jedisCluster;

    @Override
    public Long del(String key) {
        return jedisCluster.del(key);
    }

    @Override
    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    @Override
    public Long expire(String key, int seconds) {
        return jedisCluster.expire(key, seconds);
    }

    @Override
    public Long ttl(String key) {
        return jedisCluster.ttl(key);
    }
}
