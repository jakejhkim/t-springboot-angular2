package com.wirebarley.test.angular2.service;

import com.wirebarley.test.angular2.domain.Post;
import com.wirebarley.test.angular2.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jake on 2016. 8. 15..
 */
@Service
public class PostServiceImpl implements PostService<Post> {
    @Autowired
    PostRepository repository;

    @PostConstruct
    public void setUp() {
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            posts.add(Post.builder().title("Post: " + i).content("Content: " + i).build());
        }
        repository.save(posts);
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public void delete(Post post) {
        repository.delete(post);
    }

    @Override
    public Post findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }
}
