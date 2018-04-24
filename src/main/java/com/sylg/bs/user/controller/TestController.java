package com.sylg.bs.user.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sylg.bs.user.bean.UserInfo2;
import com.sylg.bs.user.service.UserService;

@RestController
@RequestMapping("test")
public class TestController {
	public volatile String params;
	@Autowired
	private UserService userService;

	@RequestMapping("t5")
	public String t5(){
		return params;
	}
	@RequestMapping("t1")
	public List<UserInfo2> t6(){
		System.out.println(userService.getByName2());
		return userService.getByName2();
	}
	
	@RequestMapping("t2")
	public Integer t7(){
		String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		return userService.getByName3(time);
	}
}
