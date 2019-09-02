/**
 * 
 */
package com.jasper.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.jasper.web.filter.TimeFilter;
import com.jasper.web.interceptor.TimeInterceptor;

/**
 * @author jasper
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private TimeInterceptor timeInterceptor;

	// interceptors will handle all controller
	// (spring own controller and the controller written by you)
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}

	// we can add the third party filter at here
	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		filterRegistrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<>();
		urls.add("/*");
		filterRegistrationBean.setUrlPatterns(urls);

		return filterRegistrationBean;
	}

}
