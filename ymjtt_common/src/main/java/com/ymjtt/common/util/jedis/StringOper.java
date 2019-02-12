package com.ymjtt.common.util.jedis;

import org.springframework.stereotype.Component;

/**
 * @auther ywx
 * @date 2019/1/24 22:32
 **/
@Component
public class StringOper extends JedisOper {

    public String set(String key, String value) {
        return jedisCluster.set(key, value);
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    //自增长1,具体原子性操作特征
    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    //自减1,具体原子性操作特征
    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

}
