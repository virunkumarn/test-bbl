package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl  implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name + "!";
    }



}
