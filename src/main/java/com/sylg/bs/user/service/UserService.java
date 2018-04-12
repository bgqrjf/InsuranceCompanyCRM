package com.sylg.bs.user.service;

import java.util.List;
import com.sylg.bs.user.bean.UserInfo;
import com.sylg.bs.user.bean.UserInfo2;

public interface UserService {
	    public UserInfo getByName( String name);
	    public int insert(String name,Integer age);
	    public List<UserInfo2> getByName2();
	    public Integer getByName3(String time);
	    }
