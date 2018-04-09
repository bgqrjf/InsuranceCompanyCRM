package com.sylg.bs.common.config.redis;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;
@RestController
public class RedisDemo {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedsissonConfig redissonConfig;
	@Autowired
	private JedisCluster js;
	
	/**
	 * 
	 * @Description:redisson测试
	 * @author QSNP242
	 * @return String
	 * @throws
	 * @date 2018年3月29日
	 */
	@RequestMapping("/redisTest")
	public String enter() {
		String s = null;
		RedissonClient client = null;
		RLock lock = null;
		try {
			client = redissonConfig.redisson();
			lock = client.getLock("shuaibi");
			lock.tryLock(100, 10, TimeUnit.SECONDS);
			js.set(Thread.currentThread().getName(), "66666");
			System.out.println(stringRedisTemplate.keys("*").toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return s;
	}
}
