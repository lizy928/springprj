package com.lizy.springprj.redis.base.impl;

import com.lizy.springprj.constant.LogConstant;
import com.lizy.springprj.constant.RedisConstant;
import com.lizy.springprj.redis.base.BaseRedis;
import com.lizy.springprj.redis.base.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;

/**
 * Created By Lizhengyuan on 19-2-11
 */
@Service
public class RedisServiceImpl implements RedisService {
    //private static final Logger log = LogConstant.commonLog;

    @Autowired
    private BaseRedis baseRedis;

    @Override
    public Long inCr(final String key) {
        Long commandResult = new RedisCommand<Long>(){
            @Override
            Long execute(JedisCluster jedis) throws IOException {
                byte[] keyBytes = encodeBytes(key);
                Long result = jedis.incr(key);
                return result == null ? 0L : result;
            }

            @Override
            Boolean checkCommandParam() {
                return null == key;
            }
        }.command();
        if (commandResult == null) {
            commandResult = 0L;
        }
        return commandResult;
    }

    @Override
    public Long deCr(String key) {
        return null;
    }

    @Override
    public Boolean isKeyExist(final String key) {
        Boolean commandResult = new RedisCommand<Boolean>() {
            @Override
            public Boolean execute(JedisCluster jedis) throws IOException {
                return jedis.exists(wrappedKey(key));
            }

            @Override
            public Boolean checkCommandParam() {
                return null == key;
            }
        }.command();
        if (commandResult == null) {
            return Boolean.FALSE;
        }
        return commandResult;
    }

    @Override
    public Long del(final String key) {
        Long commandResult = new RedisCommand<Long>(){

            @Override
            Long execute(JedisCluster jedis) throws IOException {
                byte[] keyBytes = encodeBytes(key);
                Long result = 0L;
                if (jedis.exists(keyBytes)) {
                    result = jedis.del(keyBytes);
                }
                return result == null ? 0L : result;
            }

            @Override
            Boolean checkCommandParam() {
                return null == key;
            }
        }.command();
        if(commandResult == null){
            commandResult = 0L;
        }
        return commandResult;
    }

    @Override
    public Long expire(String key, int seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public Boolean set(final String key, final String value) {
        Boolean commandResult = new RedisCommand<Boolean>(){

            @Override
            Boolean execute(JedisCluster jedis) throws IOException {
                Boolean result = StringUtils.equalsIgnoreCase(RedisConstant.REDIS_DEFAULT_OK, jedis.set(wrappedKey(key), value));
                return result;
            }

            @Override
            Boolean checkCommandParam() {
                return null == key;
            }
        }.command();
        if (commandResult == null) {
            return Boolean.FALSE;
        }
        return commandResult;
    }

    private String wrappedKey(String key) {
        return key;
    }
    private byte[] encodeBytes(String key, String charset) throws IOException {
        return wrappedKey(key).getBytes(charset);
    }
    private byte[] encodeBytes(String key) throws IOException {
        return encodeBytes(key, "UTF-8");
    }

    @Override
    public JedisCluster getJedisCluster() {
        return baseRedis.getJedisCluster();
    }

    abstract class RedisCommand<T> {
        abstract T execute(JedisCluster jedis) throws IOException;

        abstract Boolean checkCommandParam();

        public T command() {
            if (checkCommandParam()) {
                return null;
            }
            JedisCluster jedisCluster = getJedisCluster();
            try {
                return execute(jedisCluster);
            } catch (Exception e) {
               // log.warn("RedisCommand error", e);
            }
            return null;
        }
    }
}
