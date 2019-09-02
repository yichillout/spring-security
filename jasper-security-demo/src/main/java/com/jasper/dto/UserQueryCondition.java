package com.jasper.dto;

import io.swagger.annotations.ApiModelProperty;

public class UserQueryCondition {

	private String username;

	@ApiModelProperty(value = "user age start value")
	private int age;

	@ApiModelProperty(value = "user age end value")
	private int ageTo;

	private String xxx;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(int ageTo) {
		this.ageTo = ageTo;
	}

	public String getXxx() {
		return xxx;
	}

	public void setXxx(String xxx) {
		this.xxx = xxx;
	}

}
