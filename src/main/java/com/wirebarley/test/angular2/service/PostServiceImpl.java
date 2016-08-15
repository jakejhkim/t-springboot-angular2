package com.wirebarley.test.angular2.service;

import com.wirebarley.test.angular2.domain.Post;
import com.wirebarley.test.angular2.domain.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jake on 2016. 8. 15..
 */
@Service
public class PostServiceImpl implements PostService<Post> {
    @Autowired
    PostRepository repository;


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
