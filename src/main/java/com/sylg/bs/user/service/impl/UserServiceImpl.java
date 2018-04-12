package com.sylg.bs.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sylg.bs.user.bean.UserInfo;
import com.sylg.bs.user.bean.UserInfo2;
import com.sylg.bs.user.dao.UserMapper;
import com.sylg.bs.user.service.UserService;
@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserMapper usermapper;

	@Override
	public UserInfo getByName(String name) {
		return usermapper.getByName(name);
	}

	@Override
	public int insert(String name, Integer age) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserInfo2> getByName2() {
		// TODO Auto-generated method stub
		return usermapper.getByName2();
	}

	@Override
	public Integer getByName3(String time) {
		// TODO Auto-generated method stub
		return usermapper.getByName3(time);
	}

}
