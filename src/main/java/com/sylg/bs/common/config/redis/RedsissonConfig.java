package com.sylg.bs.common.config.redis;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * 
 * ClassName: RedsissonConfig 
 * @Description: redisson 客户端配置
 * @author QSNP242
 * @date 2018年4月4日
 */
	@Configuration
	@ComponentScan
	public class RedsissonConfig {
		@Value("classpath:redis.yaml") 
		Resource configFile;
		/**
		 * 
		 * @Description: 获取redisson客户端对象
		 * @author QSNP242
		 * @return
		 * @throws IOException  RedissonClient
		 * @throws
		 * @date 2018年4月4日
		 */
	    RedissonClient redisson() throws IOException {
	        Config config = Config.fromYAML(configFile.getInputStream());
	        return Redisson.create(config);
	    }
	}


