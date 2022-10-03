package com.dendtal.api.controller;

import com.dendtal.api.request.PostCreate;
import com.dendtal.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    public final PostService postService;

    // ctrl + shift + t  = 테스트 케이스 자동 생성
    @PostMapping ("/posts")
    //public String post(@RequestParam String title, @RequestParam String content){
    //public String post(@RequestParam Map<String,String> params){

    public Map<String, String> post(@RequestBody @Valid PostCreate request) {  // ModelAttribute는 생략가능
        postService.write(request);
        return Map.of();
    }



}
