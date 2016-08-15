package com.wirebarley.test.angular2.service;

import com.wirebarley.test.angular2.domain.Post;

import java.util.List;

/**
 * Created by jake on 2016. 8. 15..
 */
public interface PostService<T extends Post> {
    /**
     * Create
     * @param t
     * @return
     */
    T save(T t);

    void delete(T t);

    /**
     * Find by id
     * @param id
     * @return
     */
    T findOne(Long id);

    /**
     * Find all
     * @return
     */
    List<T> findAll();
}
