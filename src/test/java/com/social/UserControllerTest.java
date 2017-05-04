package com.social;

import com.social.controllers.PostController;
import com.social.controllers.UserController;
import com.social.entities.BasicUser;
import com.social.entities.SocialUser;
import com.social.entities.User;
import com.social.models.viewModels.UserViewModel;
import com.social.services.BasicUserService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BasicUserService userService;

    @Before
    public void setUp(){
        List<UserViewModel> users = new ArrayList<>();

        UserViewModel user1 = new UserViewModel();
        user1.setId(1L);
        user1.setUsername("TestUser");
        user1.setIsFriendsWith(true);

        UserViewModel user2 = new UserViewModel();
        user2.setId(2L);
        user2.setUsername("TestUser2");
        user2.setIsFriendsWith(false);

        users.add(user1);
        users.add(user2);

        when(userService.getUsers(null)).thenReturn(users); //fix
    }

    @Test
    public void testGettingAllUsers() throws Exception{
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("username", is("TestUser")),
                                hasProperty("isFriendsWith", is(true))
                        )
                )))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("username", is("TestUser2")),
                                hasProperty("isFriendsWith", is(false))
                        )
                )));

    }
}
