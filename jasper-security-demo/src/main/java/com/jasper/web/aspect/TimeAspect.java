package com.jasper.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 
 * @author jasper
 *
 */
@Aspect
@Component
public class TimeAspect {

	@Around("execution(* com.jasper.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {

		System.out.println("time aspect start");

		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
		}

		long start = new Date().getTime();

		Object object = pjp.proceed();

		System.out.println("time aspect spend time:" + (new Date().getTime() - start));

		System.out.println("time aspect end");

		return object;
	}

}
