package com.ymjtt.common.redis;


/**
 * com.ymjtt.common.util.redis包采用了模板设计模式
 * 封装redis的基本操作
 * @auther ywx
 * @date 2019/3/6 9:44
 **/
public interface IBaseRedisOper {

    //key存在时,删除key
    Long del(String key);

    //某个键是否存在
    Boolean exists(String key);

    //设置过期时间, 以秒计
    Long expire(String key, int seconds);

    //查看剩余时间, 以秒计
    Long ttl(String key);

}
