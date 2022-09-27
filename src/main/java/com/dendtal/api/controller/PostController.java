package com.dendtal.api.controller;

import com.dendtal.api.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
public class PostController {

    // ctrl + shift + t  = 테스트 케이스 자동 생성
    @PostMapping ("/posts")
    //public String post(@RequestParam String title, @RequestParam String content){
    //public String post(@RequestParam Map<String,String> params){
    public String post(@RequestBody PostCreate params){  // ModelAttribute는 생략가능
        log.info("params={}",params.toString());
        return "Hello World";
    }



}
