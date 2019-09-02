package com.jasper.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

//@Component
public class TimeFilter implements Filter {

	@Override
	public void destroy() {
		System.out.println("time filter destory");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("time filter start");
		long startTime = new Date().getTime();
		chain.doFilter(request, response);
		System.out.println("time filter spend time: " + (new Date().getTime() - startTime));
		System.out.println("time filter finish");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("time filter init");
	}

}
