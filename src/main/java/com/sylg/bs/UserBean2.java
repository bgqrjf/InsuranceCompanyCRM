package com.sylg.bs;

import java.io.Serializable;
import java.util.List;

public class UserBean2 implements Serializable {
private static final long serialVersionUID = 1L;
private String name1;
private Integer age1;
private List<Integer> sport;

public final String getName1() {
	return name1;
}

public final void setName1(String name1) {
	this.name1 = name1;
}

public final Integer getAge1() {
	return age1;
}

public final void setAge1(Integer age1) {
	this.age1 = age1;
}

public final List<Integer> getSport() {
	return sport;
}

public final void setSport(List<Integer> sport) {
	this.sport = sport;
}

@Override
public String toString() {
	return "UserBean2 [name1=" + name1 + ", age1=" + age1 + ", sport=" + sport
			+ "]";
}



}
