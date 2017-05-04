package com.social;

import com.social.controllers.PostController;
import com.social.models.viewModels.PostViewModel;
import com.social.repositories.BasicUserRepository;
import com.social.repositories.PostRepository;
import com.social.services.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@ActiveProfiles("test")
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Before
    public void setUp() throws Exception {
        PostViewModel post1 = new PostViewModel();
        post1.setUsername("TestUser1");
        post1.setContent("test content1");
        post1.setId(1L);
        PostViewModel post2 = new PostViewModel();
        post2.setUsername("TestUser2");
        post2.setContent("test content2");
        List<PostViewModel> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        post2.setId(2L);

        when(postService.index()).thenReturn(posts);
    }

    @Test
    public void showAllPosts() throws Exception {
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("posts", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("username", is("TestUser1")),
                                hasProperty("content", is("test content1"))
                        )
                )))
                .andExpect(model().attribute("posts", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("username", is("TestUser2")),
                                hasProperty("content", is("test content2"))
                        )
                )));
    }
}