package com.sylg.bs.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sylg.bs.user.bean.UserInfo;
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

}
