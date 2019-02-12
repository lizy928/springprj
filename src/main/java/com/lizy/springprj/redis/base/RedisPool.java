package com.lizy.springprj.redis.base;

import com.lizy.springprj.constant.CommonConstant;
import com.lizy.springprj.util.PropertyUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * Created by kyle on 2017/6/17.
 */
public class RedisPool {

    protected JedisCluster redisCluster;
    protected GenericObjectPoolConfig config;

    /**
     * spring注入的时候立即执行初始化
     */
    public void initPool(String prefix) {
        if (config == null) {
            config = new GenericObjectPoolConfig();
            // Maximum active connections to Redis instance
            config.setMaxTotal(PropertyUtils.getPropertyIntValue(prefix + ".redis_max_active", 3000));
            // Number of connections to Redis that just sit there and do nothing
            config.setMaxIdle(PropertyUtils.getPropertyIntValue(prefix + ".redis_max_idle", 300));
            // Minimum number of idle connections to Redis
            // these can be seen as always open and ready to serve
            config.setMinIdle(PropertyUtils.getPropertyIntValue(prefix + ".redis_min_idle", 100));
            config.setMaxWaitMillis(PropertyUtils.getPropertyIntValue(prefix + ".redis_max_wait", 2000));//ms
        }
        if (null == redisCluster) {
            HashSet<HostAndPort> hostAndPortSet = new HashSet<>();
            String hostAndPortStr = PropertyUtils.getProperty(prefix + ".redis_cluster");
            String[] hostAndPortArray = hostAndPortStr.split(CommonConstant.COMMON_ESCAPE_STR + CommonConstant
                    .COMMA_SPLIT_STR);
            for (String hostAndPort : hostAndPortArray) {
                String[] ipAndPort = hostAndPort.split(CommonConstant.COMMON_ESCAPE_STR + CommonConstant
                        .COMMON_COLON_STR);
                HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                hostAndPortSet.add(hap);
            }
            int timeout = PropertyUtils.getPropertyIntValue(prefix + ".redis_time_out", 10000);
            int maxRedirection = PropertyUtils.getPropertyIntValue(prefix + ".redis_max_redirection", 3);
            redisCluster = new JedisCluster(hostAndPortSet, timeout, timeout, maxRedirection, config);
        }
    }

    /**
     * 获取jedis
     *
     * @return
     */
    public JedisCluster getJedisCluster() {
        return redisCluster;
    }
}
