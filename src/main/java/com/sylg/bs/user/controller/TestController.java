package com.sylg.bs.user.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sylg.bs.user.service.UserService;

@RestController
@RequestMapping("test")
public class TestController {
	@Value("#{userBean.name}")
	public volatile String params;
	@Autowired
	private UserService userService;

	@RequestMapping("t5")
	public String t5(){
		return params;
	}
}
