package com.sylg.bs.common.config.kafka;

import lombok.extern.slf4j.Slf4j;

import org.apache.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
/**
 * 
 * ClassName: Consumer 
 * @Description: 消费者
 * @author QSNP242
 * @date 2018年4月8日
 */

@Component
@Slf4j
public class Consumer {
	private Logger log = Logger.getLogger(Consumer.class);
	 /**
     * 监听demo主题,有消息就读取
     * @param message
     */
    @KafkaListener(topics = {"demo","sbKafka"})
    public void consumer(String message){
    	log.info("message========   "+message);
    	System.err.println(message);
    }
}
