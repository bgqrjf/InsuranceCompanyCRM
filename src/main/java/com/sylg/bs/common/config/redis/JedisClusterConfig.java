package com.sylg.bs.common.config.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
/**
 * 
 * ClassName: JedisClusterConfig 
 * @Description: Jedis客户端
 * @author QSNP242
 * @date 2018年4月4日
 */
@Configuration  
@ConditionalOnClass({ JedisCluster.class })  
public class JedisClusterConfig {  
  
    @Value("${spring.redis.cluster.nodes}")  
    private String clusterNodes;  
  
    @Value("${spring.redis.timeout}")  
    private Integer commandTimeout;  
  
    @Bean  
    public JedisCluster getJedisCluster() {  
  
        String[] serverArray = clusterNodes.split(",");  
        Set<HostAndPort> nodes = new HashSet<>();  
        for (String ipPort : serverArray) {  
            String[] ipPortPair = ipPort.split(":");  
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));  
        }  
        return new JedisCluster(nodes, commandTimeout);  
    }  
  
}  