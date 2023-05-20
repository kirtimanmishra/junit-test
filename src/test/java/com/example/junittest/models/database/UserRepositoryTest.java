package com.example.junittest.models.database;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;

  @Test
  void findByNameTest() {
    String userName = "test-user";
    User user = User
        .builder()
        .name(userName)
        .age(30)
        .build();
    userRepository.save(user);
    List<User> users = userRepository.findByName(userName);
    Assertions.assertTrue(users.size()>0);
    Assertions.assertEquals(user,users.get(0));
  }

  @AfterEach
  void tearDown() {
    userRepository.deleteAll();
  }
}