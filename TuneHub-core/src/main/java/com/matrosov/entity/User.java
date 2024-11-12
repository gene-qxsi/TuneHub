package com.matrosov.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    Integer id;
    String username;
    String password;
    String email;
    LocalDate birthday;
    Role role;
    Gender gender;
    LocalDateTime created_at;
}
