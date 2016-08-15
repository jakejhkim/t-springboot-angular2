package com.wirebarley.test.angular2.api;

import com.wirebarley.test.angular2.api.dto.PostData;
import com.wirebarley.test.angular2.api.dto.PostData.PostListResData;
import com.wirebarley.test.angular2.api.dto.PostData.PostResData;
import com.wirebarley.test.angular2.domain.Post;
import com.wirebarley.test.angular2.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jake on 2016. 8. 15..
 */
@Slf4j
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<PostListResData> list() {
        List<Post> posts = postService.findAll();
        List<PostResData> postResDataList = posts.stream()
                .map(post -> modelMapper.map(post, PostResData.class)).collect(Collectors.toList());
        PostListResData res = PostListResData.builder().list(postResDataList).build();
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<PostResData> create(@Valid @RequestBody Post post, BindingResult bindingResult) {
        log.debug("Create: {}", post);
        if(bindingResult.hasErrors()) {
            log.error("Entity errors: {}", bindingResult);
        }
        postService.save(post);
        return ResponseEntity.ok(modelMapper.map(post, PostResData.class));
    }

    @GetMapping("/{post}")
    public ResponseEntity<PostResData> get(@PathVariable Post post) {
        if(post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostData.PostResData());
        }
        return ResponseEntity.ok(modelMapper.map(post, PostResData.class));
    }

    @PutMapping("/{post}")
    public ResponseEntity<PostResData> modify(@PathVariable Post post, @RequestBody PostData req) {
        if(post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostData.PostResData());
        }
        modelMapper.map(req, post);
        postService.save(post);
        return ResponseEntity.ok(modelMapper.map(post, PostResData.class));
    }

    @DeleteMapping("/{post}")
    public ResponseEntity<PostResData> delete(@PathVariable Post post) {
        if(post == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new PostData.PostResData());
        }
        postService.delete(post);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new PostData.PostResData());
    }

}
