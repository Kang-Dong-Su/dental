package com.dendtal.api.controller;

import com.dendtal.api.request.PostCreate;
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
public class PostController {

    // ctrl + shift + t  = 테스트 케이스 자동 생성
    @PostMapping ("/posts")
    //public String post(@RequestParam String title, @RequestParam String content){
    //public String post(@RequestParam Map<String,String> params){
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) {  // ModelAttribute는 생략가능
        log.info("params={}",params.toString());

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // title
            String errorMessage = firstFieldError.getDefaultMessage(); // ...에러 메시지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }

        return Map.of();
    }



}
