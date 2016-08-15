package com.wirebarley.test.angular2.api;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public abstract class BaseMockWebTest {
    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void before() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(print()).build();
    }

    @Autowired
    protected MappingJackson2HttpMessageConverter httpMessageConverter;

    protected String convertToJson(Object obj) throws IOException {
        return httpMessageConverter.getObjectMapper().writeValueAsString(obj);
    }

    protected <T> T convertFromJson(String json, Class<T> clazz) throws IOException {
        return httpMessageConverter.getObjectMapper().readValue(json, clazz);
    }

    protected <T> T convertFromJson(MvcResult mvcRes, Class<T> clazz) throws IOException {
        return convertFromJson(mvcRes.getResponse().getContentAsString(), clazz);
    }
}