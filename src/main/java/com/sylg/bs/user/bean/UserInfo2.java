package com.sylg.bs.user.bean;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;
public class UserInfo2 implements Serializable {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;


	private String testDate;


	public final String getTestDate() {
		return testDate;
	}


	public final void setTestDate(String testDate) {
		this.testDate = testDate;
	}


	@Override
	public String toString() {
		return "UserInfo2 [testDate=" + testDate + "]";
	}


	

	
}
