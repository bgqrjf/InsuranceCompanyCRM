package com.sylg.bs.common.config.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "spark")
public class SparkContextBean {
	private String sparkHome;
	private String appName;
	private String master;

	/**
	 * 
	 * @Description: spring生成SparkConf对象
	 * @author QSNP242
	 * @return
	 * @throws Exception
	 *             SparkConf
	 * @throws
	 * @date 2018年4月12日
	 */
	@Bean
	/*
	 * 该注解表示，如果存在它修饰的类的bean，则不需要再创建这个bean；
	 * 这个表示如果SparkConf.class的bean存在，这该注解修饰的代码块不执行。
	 */
	@ConditionalOnMissingBean(SparkConf.class)
	public SparkConf sparkConf() throws Exception {
		SparkConf conf = new SparkConf().setSparkHome(sparkHome)
				.setAppName(appName).setMaster(master).set("spark.driver.allowMultipleContexts", "true");
		return conf;
	}

	/**
	 * 
	 * @Description: 生成SparkContext对象
	 * @author QSNP242
	 * @return
	 * @throws Exception
	 *             JavaSparkContext
	 * @throws
	 * @date 2018年4月12日
	 */
	/*@Bean
	@ConditionalOnMissingBean(JavaSparkContext.class)*/
	public JavaSparkContext javaSparkContext() throws Exception {
		
		return new JavaSparkContext(sparkConf());
		
	}
	/**
	 * 
	 * @Description: spark-streamingContext对象 和上面的JavaSparkContext对象冲突
	 * @author QSNP242
	 * @return  JavaStreamingContext
	 * @throws Exception 
	 * @throws
	 * @date 2018年4月13日
	 */
	@Bean
	@ConditionalOnMissingBean(JavaStreamingContext.class)
	public JavaStreamingContext javaStreamingContext() throws Exception{
		  JavaStreamingContext jssc = new JavaStreamingContext(sparkConf(), Durations.seconds(5));
		  return jssc;
	}

	public String getSparkHome() {
		return sparkHome;
	}

	public void setSparkHome(String sparkHome) {
		this.sparkHome = sparkHome;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
}
