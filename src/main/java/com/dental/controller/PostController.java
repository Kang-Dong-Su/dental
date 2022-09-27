package com.dental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {


    // ctrl + shift + t  = 테스트 케이스 자동 생성
    @PostMapping ("/posts")
    public String post(){
        return "Hello World";
    }



}
