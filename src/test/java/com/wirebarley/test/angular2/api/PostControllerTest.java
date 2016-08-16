package com.wirebarley.test.angular2.api;

import com.wirebarley.test.angular2.api.dto.PostData;
import com.wirebarley.test.angular2.api.dto.PostData.PostListResData;
import com.wirebarley.test.angular2.api.dto.PostData.PostResData;
import com.wirebarley.test.angular2.domain.Post;
import com.wirebarley.test.angular2.domain.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jake on 2016. 8. 15..
 */
@Slf4j
public class PostControllerTest extends BaseMockWebTest {

    @Autowired
    PostRepository repository;

    @Test
    public void add() throws Exception {
        String title = "Test";
        String content = "Test content";
        Post targetPost = Post.builder().title(title).content(content).build();

        log.debug("targetPost: {}", convertToJson(targetPost));

        MvcResult result = this.mockMvc
                .perform(
                        post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(convertToJson(targetPost))
                )
                .andExpect(status().isOk())
                .andReturn();

        PostResData res = convertFromJson(result, PostResData.class);
        assertThat(res.getId()).isNotNull();
        assertThat(res.getTitle()).isEqualTo(title);
        assertThat(res.getContent()).isEqualTo(content);
    }

    @Test
    public void findAll() throws Exception {
        generatePost(10);

        MvcResult result = this.mockMvc
                .perform(get("/api/posts").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();

        PostListResData res = convertFromJson(result, PostListResData.class);
        assertThat(res.getPosts().size()).isEqualTo(20);
    }

    @Test
    public void getPost() throws Exception {
        generatePost(10);
        List<Post> posts = repository.findAll();
        Post targetPost = posts.get(new Random().nextInt(posts.size()));

        MvcResult result = this.mockMvc
                .perform(get("/api/posts/{post}", targetPost.getId()))
                .andExpect(status().isOk()).andReturn();

        PostResData res = convertFromJson(result, PostResData.class);
        assertThat(res.getId()).isEqualTo(targetPost.getId());
        assertThat(res.getTitle()).isEqualTo(targetPost.getTitle());
    }

    @Test
    public void 없는대상을_가져오려고할때() throws Exception {
        this.mockMvc.perform(get("/api/posts/{post}", Long.MAX_VALUE)).andExpect(status().isBadRequest());
    }

    @Test
    public void modifyPost() throws Exception {
        generatePost(10);
        List<Post> posts = repository.findAll();
        Post targetPost = posts.get(new Random().nextInt(posts.size()));

        String title = "Modify title";
        String content = "Modify content";
        PostData postData = PostData.builder().title(title).content(content).build();
        MvcResult result = this.mockMvc.perform(
                put("/api/posts/{post}", targetPost.getId())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(convertToJson(postData)))
                .andExpect(status().isOk())
                .andReturn();

        PostResData res = convertFromJson(result, PostResData.class);
        assertThat(res.getTitle()).isEqualTo(title);
        assertThat(res.getContent()).isEqualTo(content);
    }

    @Test
    public void deletePost() throws Exception {
        generatePost(10);
        List<Post> posts = repository.findAll();
        Post targetPost = posts.get(new Random().nextInt(posts.size()));

        this.mockMvc.perform(delete("/api/posts/{post}", targetPost.getId())).andExpect(status().isNoContent());

        assertThat(repository.findOne(targetPost.getId())).isNull();
    }

    @Test
    public void 없는대상을_지우려고할때() throws Exception {
        this.mockMvc.perform(delete("/api/posts/{post}", Long.MAX_VALUE)).andExpect(status().isBadRequest());
    }

    private void generatePost(int size) {
        for(int i = 0; i < size; i++) {
            repository.save(Post.builder().title("Post " + i).content("Post content " + i).build());
        }
    }
}