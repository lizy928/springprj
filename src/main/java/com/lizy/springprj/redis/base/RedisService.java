package com.lizy.springprj.redis.base;

import redis.clients.jedis.JedisCluster;

/**
 * Created By Lizhengyuan on 19-2-11
 */
public interface RedisService {

    Long inCr(String key);

    Long deCr(String key);

    Boolean isKeyExist(String key);

    Long del(String key);

    Long expire(String key, int seconds);

    String get(String key);

    Boolean set(String key, String value);

    JedisCluster getJedisCluster();

}
