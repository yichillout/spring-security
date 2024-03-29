package com.jasper.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author jasper
 *
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("time interceptor preHandle");

		System.out.println("time interceptor : handler : " + ((HandlerMethod) handler).getBean().getClass().getName());
		System.out.println("time interceptor : handler : " + ((HandlerMethod) handler).getMethod().getName());

		request.setAttribute("startTime", new Date().getTime());
		return true; // if true will call the controller method
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("time interceptor postHandle");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor spend time:" + (new Date().getTime() - start));
	}

	// even though throw exception, it will go to afterCompletion method
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("time interceptor afterCompletion");
		Long start = (Long) request.getAttribute("startTime");
		System.out.println("time interceptor spend time:" + (new Date().getTime() - start));
		System.out.println("ex is " + ex);
	}

}
