package com.jasper.service.impl;

import org.springframework.stereotype.Service;

import com.jasper.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String greeting(String name) {
		System.out.println("greeting " + name);
		return "hello " + name;
	}

}
