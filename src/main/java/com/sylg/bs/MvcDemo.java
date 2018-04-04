package com.sylg.bs;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.jni.Lock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCluster;

@Controller
@RequestMapping("/mvc")
public class MvcDemo {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	private JedisCluster js;
	@Autowired
	private RedsissonConfig redissonConfig;

	/**
	 * 
	 * @Description: ajax访问的方法,使用实体类接收表单序列化的参数
	 * @author QSNP242
	 * @param user
	 * @return String
	 * @throws
	 * @date 2018年3月29日
	 */
	@RequestMapping("/m1")
	@ResponseBody
	public UserBean test(UserBean list) {
		System.out.println("ok");
		System.out.println(list.toString());
		return null;
	}

	/**
	 * 
	 * @Description:redisson测试
	 * @author QSNP242
	 * @return String
	 * @throws
	 * @date 2018年3月29日
	 */
	@RequestMapping("/enter")
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
	/**
	 * 
	 * @Description: REST测试
	 * @author QSNP242
	 * @param userCode
	 * @param orderUrl
	 * @return  String
	 * @throws
	 * @date 2018年4月4日
	 */
	@RequestMapping("/{userCode}")
	public String test2(@PathVariable("userCode") String userCode,
			String orderUrl) {
		System.out.println(userCode);
		System.out.println(orderUrl);
		StringBuilder sb = new StringBuilder("https://www.baidu.com");
		sb.append("?userCode="+userCode);
		return "redirect:"+sb.toString();
	}
	@RequestMapping("/jsp")
	public String test3() {
	
		return "index";
	}

}
