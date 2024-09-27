package com.example.tobi.springbootbasic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/hello") //주소창에 사용할 이름
    public String hello() {

        return "hello"; //불러들일 템플릿 이름
    }
}
