package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
	
	@RequestMapping(value = { "/say" }, method = { RequestMethod.GET })
	public String sayHello(){
		System.out.println("hello world");
		
		return "hello world!";
	}

}
