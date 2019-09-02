package com.jasper.code;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.jasper.security.core.validate.code.ImageCode;
import com.jasper.security.core.validate.code.ValidateCodeGenerator;

//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("test the @ConditionalOnMissingBean");
		return null;
	}

}