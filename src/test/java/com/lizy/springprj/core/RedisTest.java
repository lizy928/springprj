package com.lizy.springprj.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * Created By Lizhengyuan on 19-2-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring-base.xml",
        "file:src/main/webapp/WEB-INF/spring-view.xml"})
public class RedisTest {

    @Autowired
    private JedisCluster cluster;

    @Test
    public void testRedis(){
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("47.107.92.79",7001));
        nodes.add(new HostAndPort("47.107.92.79",7002));
        nodes.add(new HostAndPort("47.107.92.79",7003));
        nodes.add(new HostAndPort("47.107.92.79",7004));
        nodes.add(new HostAndPort("47.107.92.79",7005));
        nodes.add(new HostAndPort("47.107.92.79",7006));
        JedisCluster cluster = new JedisCluster(nodes);

        String test2 = cluster.get("test2");
        System.out.println(test2);
        try {
            cluster.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
