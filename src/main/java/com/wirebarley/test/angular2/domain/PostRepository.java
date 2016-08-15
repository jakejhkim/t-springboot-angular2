package com.wirebarley.test.angular2.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jake on 2016. 8. 15..
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
