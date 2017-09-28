package com.meepo.mybatis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/*
 * redis连接池
 */
public class JedisPoolUtil {
	private static volatile JedisPool jedisPool = null;
	private static String AUTH = "thisisatestpasswordsetat201708021411.";
	private static int TIMEOUT = 10000;
	
	private JedisPoolUtil() {
	}

	public static JedisPool getJedisPoolInstance() {

		if (jedisPool == null) {
			synchronized (JedisPoolUtil.class) {
				if (jedisPool == null) {
					JedisPoolConfig poolConfig = new JedisPoolConfig();
					poolConfig.setMaxActive(1000);
					poolConfig.setMaxIdle(32);
					poolConfig.setMaxWait(100 * 1000);
					poolConfig.setTestOnBorrow(true);
					jedisPool = new JedisPool(poolConfig, "192.168.1.244", 6379,TIMEOUT,AUTH);
				}
			}
		}
		return jedisPool;
	}

	public static void release(JedisPool jedisPool, Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResourceObject(jedis);
		}
	}

	/*
	 * save to redis
	 */

/*	public static void saveToRedis(String key, String value) {
		JedisPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisPoolUtil.release(jedisPool, jedis);
		}

	}*/

}
