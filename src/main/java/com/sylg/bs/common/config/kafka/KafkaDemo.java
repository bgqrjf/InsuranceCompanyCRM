package com.sylg.bs.common.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaDemo {
	@Autowired
	private Producer producer;
	@RequestMapping("/kafka")
	public String testKafka() {
		producer.sendTest();
		return "ok";
	}
}
