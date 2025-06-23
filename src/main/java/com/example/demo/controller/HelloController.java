package com.example.demo.controller;
import lombok.AllArgsConstructor;

import com.example.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor

public class HelloController {


	private final HelloService helloService;


    @GetMapping("/")
	public String index() {
		return helloService.sayHello("World");
	}

}
