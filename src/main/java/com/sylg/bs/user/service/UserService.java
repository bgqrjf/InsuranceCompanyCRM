package com.sylg.bs.user.service;

import com.sylg.bs.user.bean.UserInfo;

public interface UserService {
	    public UserInfo getByName( String name);
	    public int insert(String name,Integer age);
}
