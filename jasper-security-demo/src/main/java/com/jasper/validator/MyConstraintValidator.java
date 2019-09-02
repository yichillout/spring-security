package com.jasper.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jasper.service.HelloService;

/**
 * 
 * @author jasper
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private HelloService helloService;

	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting((String) value);
		System.out.println("isValid method : " + value);
		if (!StringUtils.isBlank((String) value) && ((String) value).equals("Jasper")) {
			return true;
		} else {
			return false;
		}
	}

}
