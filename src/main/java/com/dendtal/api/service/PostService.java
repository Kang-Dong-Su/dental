package com.dendtal.api.service;

import com.dendtal.api.domain.Post;
import com.dendtal.api.repository.PostRepository;
import com.dendtal.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void write(PostCreate postCreate) {
        // 일반 class인 postCreate -> Entity화

        Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        postRepository.save(post);


    }
}
