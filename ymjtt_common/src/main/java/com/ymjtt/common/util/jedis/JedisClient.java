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
public class JedisClient {

	@Autowired						//idea建议这里用构造注入的方式
	private JedisCluster jedisCluster;

	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	public String get(String key) {
		return jedisCluster.get(key);
	}

	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	public Long expire(String key, int seconds) {
		return jedisCluster.expire(key, seconds);
	}

	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}

	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	public Long hset(String key, String field, String value) {
		return jedisCluster.hset(key, field, value);
	}

	public String hget(String key, String field) {
		return jedisCluster.hget(key, field);
	}

	public Long hdel(String key, String... field) {
		return jedisCluster.hdel(key, field);
	}

}
