package com.dendtal.api.service;

import com.dendtal.api.domain.Post;
import com.dendtal.api.repository.PostRepository;
import com.dendtal.api.request.PostCreate;
import com.dendtal.api.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {


    //  field injection은 비추
   //@Autowired private PostRepository postRepository;

    private final PostRepository postRepository;

    /*
    @RequiredArgsConstructor 와 같다

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    */

    public Long write(PostCreate postCreate) {
        // 일반 class인 postCreate -> Entity화

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);

        return post.getId();
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

        /**
         * 이 변환 작업을 여기서 하는게 맞냐?는 말들도 있다
         *
         * 호돌맨님은
         * PostController -> WebService -> Repository
         *                  PostService
         */

    }


    public List<PostResponse> getList() {
        return postRepository.findAll().stream()
                //.map(post ->new PostResponse(post))
                .map(PostResponse::new)
                .collect(Collectors.toList());
        /*
        return postRepository.findAll().stream()
                .map(post ->PostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
        */
    }
}
