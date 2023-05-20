package com.example.junittest.models.database;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
  @Query("{name : ?0}")                                         // SQL Equivalent : SELECT * FROM BOOK where author = ?
  List<User> findByName(String name);
}
