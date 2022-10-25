package com.dendtal.api.controller;

import com.dendtal.api.domain.Post;
import com.dendtal.api.request.PostCreate;
import com.dendtal.api.response.PostResponse;
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
    @PostMapping("/posts")
    //public String post(@RequestParam String title, @RequestParam String content){
    //public String post(@RequestParam Map<String,String> params){

    public void post(@RequestBody @Valid PostCreate request) {  // ModelAttribute는 생략가능
        // POST -> 200, 201

        // Case1. 저장한 데이터 Entity -> response로 응답하기
        // Case2. 저장한 데이터의 primary_id -> response로 응답하기
        //          Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
        // Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
        // Bad Case : 서버에서 -> 반드시 이렇게 할겁니다! fix
        //            -> 서버에서는 차라리 유연하게 대응하는게 좋습니다 -> 코드를 잘 짜야겠죠?! ㅎ
        //            -> 한 번에 일괄적으로 ㅈ라 처리되는 케이스가 없다 -> 잘 관리하는 형태가 중요

        postService.write(request);
    }

    /**
     * /posts -> 글 전체 조회(검색+페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId) {
        //Request 클래스
        //Response 클래스

        // 서비스 정책에 맞게 응답 클래스를 분리하세요
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponse> getList() {
        return postService.getList();
    }
}
