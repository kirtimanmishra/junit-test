package com.example.junittest.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.junittest.models.database.User;
import com.example.junittest.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/*
 * for controller layer test, I have used @WebMvcTest
 */
@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private UserService userService;
  ObjectMapper objectMapper = new ObjectMapper();
  User user = User
      .builder()
      .name("kirti")
      .age(30)
      .build();

  @Test
  public void adduserTest() throws Exception {
    String jsonUser = objectMapper.writeValueAsString(user);
    when(userService.addUser(user)).thenReturn(
        ResponseEntity.ok().body(user)
    );

    this.mockMvc.perform(
            post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser)
        )
        .andExpect(status().isOk())
        .andExpect(content().string(jsonUser));
  }

  @Test
  public void findByUserNameTest() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(user);
    String jsonUsers = objectMapper.writeValueAsString(users);
    when(userService.findUserByName(user.getName())).thenReturn(
        ResponseEntity.ok().body(List.of(user))
    );

    this.mockMvc.perform(
            get("/users")
                .param("name",user.getName())
        )
        .andExpect(status().isOk())
        .andExpect(content().string(jsonUsers));
  }
}