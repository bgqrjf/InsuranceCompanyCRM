package com.sylg.bs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * ClassName: App
 * 
 * @Description: 项目启动入口
 * @author QSNP242
 * @date 2018年4月8日
 */
@SpringBootApplication
//自动管理事务
@EnableTransactionManagement
public class App extends SpringBootServletInitializer

{
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(App.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class);
	}

}