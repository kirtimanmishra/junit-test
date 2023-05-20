package com.example.junittest.services;

import com.example.junittest.models.database.User;
import com.example.junittest.models.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  RestTemplate restTemplate = new RestTemplate();

  public ResponseEntity<Object> addUser(User user){
    // only for test
    restTemplate.getForEntity("https://www.google.com/",String.class);
    return ResponseEntity.ok().body(userRepository.save(user));
  }
  public ResponseEntity<Object> findUserByName(String name){
    return ResponseEntity.ok().body(userRepository.findByName(name));
  }
}
