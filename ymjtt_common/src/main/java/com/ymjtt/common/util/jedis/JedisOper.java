package com.ymjtt.common.util.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

/**
 * Redis操作
 * @author  ywx
 * @date    2018/11/14 23:06
 */
@Component
public class JedisOper {

	@Autowired						//idea建议这里用构造注入的方式
	protected JedisCluster jedisCluster;

	//key存在时,删除key
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	//设置过期时间, 以秒计
	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	//查看剩余时间, 以秒计
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

}
