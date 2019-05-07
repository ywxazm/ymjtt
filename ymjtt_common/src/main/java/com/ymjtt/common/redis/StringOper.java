package com.ymjtt.common.redis;

import org.springframework.stereotype.Component;

/**
 * @auther ywx
 * @date 2019/3/7 9:45
 **/
@Component
public class StringOper extends AbstractBaseRedisOper implements IStringOper {

    @Override
    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    @Override
    public String get(String key) {
        return jedisCluster.get(key);
    }

    //自增长1,具体原子性操作特征
    @Override
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    //自减1,具体原子性操作特征
    @Override
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }
}
