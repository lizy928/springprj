package com.lizy.springprj.redis.base;

import com.lizy.springprj.constant.LogConstant;
import com.lizy.springprj.util.PropertyUtils;
import org.slf4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.concurrent.TimeUnit;
public class BaseRedis extends RedisPool {
    private static final Logger log = LogConstant.commonLog;
    //使用threadLocal避免释放的时候传递jedis对象
    private static ThreadLocal<Jedis> jedisLocal = new ThreadLocal<>();

    public void initPool() {
        String prefix = PropertyUtils.getProperty("springprj.prefix");
        initPool(prefix);
        log.info("BaseClusterRedis initPool over.");
    }

    public static void main(String[] args) throws InterruptedException {
        final BaseRedis baseRedis = new BaseRedis();
        baseRedis.initPool();
        for (int i = 0; i < 20; i++) {
            JedisCluster jedisCluster = baseRedis.getJedisCluster();
            jedisCluster.set(String.valueOf(i), String.valueOf(i));
            System.out.println("set " + i + ". value:" + jedisCluster.get(String.valueOf(i)));
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }
}
