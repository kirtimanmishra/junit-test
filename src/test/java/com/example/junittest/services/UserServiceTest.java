package com.example.junittest.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.junittest.models.database.User;
import com.example.junittest.models.database.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {
  @Mock
  private UserRepository userRepository;
  @Mock
  private RestTemplate restTemplate;
  @InjectMocks
  private UserService userService;
  ResponseEntity responseEntity = mock(ResponseEntity.class);
  ObjectMapper om = new ObjectMapper();

  @Test
  public void addUserTest() throws JsonProcessingException {
    User user = User
        .builder()
        .name("kirti")
        .age(30)
        .build();

    when(userRepository.save(user)).thenReturn(user);
    Mockito.when(restTemplate.getForEntity(
            Mockito.anyString(),
            ArgumentMatchers.any(Class.class)
        ))
        .thenReturn(responseEntity);

    ResponseEntity<Object> responseEntity = userService.addUser(user);
    User responseUser = (User) responseEntity.getBody();
    assertEquals(200, responseEntity.getStatusCode().value());
    assertEquals(user.getName(), responseUser.getName());
    assertEquals(user.getAge(), responseUser.getAge());
  }

  @Test
  public void findUserTest() throws JsonProcessingException {
    String name = "kirti";
    when(userRepository.findByName(name)).thenReturn(
       List.of(User.builder().name(name).age(32).build())
    );

    ResponseEntity<Object> responseEntity = userService.findUserByName(name);
    List<User> responseUsers = (List<User>) responseEntity.getBody();
    assertEquals(200, responseEntity.getStatusCode().value());
    assertEquals(name, responseUsers.get(0).getName());
    assertEquals(32, responseUsers.get(0).getAge());
  }
}