package com.jasper.security.core.properties;

public class BrowserProperties {

	private String loginPage = "/standard-login.html";

	private LoginResponseType loginResponseType = LoginResponseType.JSON;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginResponseType() {
		return loginResponseType;
	}

	public void setLoginResponseType(LoginResponseType loginResponseType) {
		this.loginResponseType = loginResponseType;
	}
}
