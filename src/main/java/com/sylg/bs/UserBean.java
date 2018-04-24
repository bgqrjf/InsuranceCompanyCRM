package com.sylg.bs;

import java.io.Serializable;
import org.springframework.stereotype.Component;
@Component
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	
}
