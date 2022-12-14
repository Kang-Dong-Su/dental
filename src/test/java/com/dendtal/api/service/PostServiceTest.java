package com.dendtal.api.service;

import com.dendtal.api.domain.Post;
import com.dendtal.api.repository.PostRepository;
import com.dendtal.api.request.PostCreate;
import com.dendtal.api.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L,postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());

    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        // given
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost);

        Long postId = 1L;

        // 클라이언트 요구사항
        // json응답에서 title값 길이를 최대 10글자로 해주세요 > 클라에서 하는건뎅...

        // when
        PostResponse response = postService.get(requestPost.getId());

        // then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("foo", response.getTitle());
        assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3() {
        // given
        /*
        Post requestPost1 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost1);

        Post requestPost2 = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        postRepository.save(requestPost2);
        */

        postRepository.saveAll(List.of(
           Post.builder()
                   .title("foo")
                   .content("bar")
                   .build(),
                Post.builder()
                    .title("foo")
                    .content("bar")
                    .build()
        ));

        // 클라이언트 요구사항
        // json응답에서 title값 길이를 최대 10글자로 해주세요 > 클라에서 하는건뎅...

        // when
        List<PostResponse> posts = postService.getList();

        // then
        assertEquals(2L, posts.size());
    }
    
    
}