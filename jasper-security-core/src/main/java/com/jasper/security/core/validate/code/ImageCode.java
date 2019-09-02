package com.jasper.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 
 * @author jasper
 *
 */
public class ImageCode {

	private BufferedImage image;

	private String code;

	private LocalDateTime expiredTime;

	public ImageCode(BufferedImage bufferedImage, String code, int expiredSec) {
		super();
		this.image = bufferedImage;
		this.code = code;
		this.expiredTime = expiredTime.now().plusSeconds(expiredSec);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(LocalDateTime expiredTime) {
		this.expiredTime = expiredTime;
	}

}
