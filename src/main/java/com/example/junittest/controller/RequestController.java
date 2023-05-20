package com.example.junittest.controller;

import com.example.junittest.models.database.User;
import com.example.junittest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class RequestController {

  @Autowired
  private UserService userService;
  @PostMapping
  public ResponseEntity<Object> addUser(@RequestBody @Validated User user){
    return userService.addUser(user);
  }
  @GetMapping
  public ResponseEntity<Object> findUserByName(@RequestParam String name){
    return userService.findUserByName(name);
  }
}
