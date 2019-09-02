package com.jasper.security.core.properties;

public class ImageCodeProperties {

	private int width = 67;
	private int height = 23;

	private int validateCodeLength = 4;
	private int expiredTime = 60;

	private String url;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getValidateCodeLength() {
		return validateCodeLength;
	}

	public void setValidateCodeLength(int validateCodeLength) {
		this.validateCodeLength = validateCodeLength;
	}

	public int getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(int expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
