package com.ymjtt.common.redis;

/**
 * @auther ywx
 * @date 2019/3/7 9:30
 **/
public interface IStringOper extends IBaseRedisOper {

    String set(String key, String value);

    String get(String key);

    //自增长1,具体原子性操作特征
    Long incr(String key);

    //自减1,具体原子性操作特征
    Long decr(String key);
}
