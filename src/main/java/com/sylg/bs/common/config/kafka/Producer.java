package com.sylg.bs.common.config.kafka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
/**
 * 
 * ClassName: Producer 
 * @Description:kafka生产者
 * @author QSNP242
 * @date 2018年4月8日
 */
@Component
public class Producer {
	      @Autowired
	    private KafkaTemplate<String,Object> kafkaTemplate;

	    /**
	     * 发送消息到kafka,主题为test
	     */
	    public void sendTest(){
	        kafkaTemplate.send("demo","hello,kafka  "  + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
	    }
}
