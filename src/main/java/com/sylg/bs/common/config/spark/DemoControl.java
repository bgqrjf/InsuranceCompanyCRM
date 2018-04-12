package com.sylg.bs.common.config.spark;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControl {
	@Autowired
	private SparkTestService sparkTestService;
	
	@RequestMapping("/demo/spark")
	public Map<String, Object> sparkDemo() {
		return sparkTestService.sparkDemo();
	}
}