package com.example.junittest.models.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class User {
  @Id
  private String id;
  @NonNull
  private String name;
  @NonNull
  private int age;
}
