package com.matrosov.dto;

import com.matrosov.entity.Gender;
import com.matrosov.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    String username;
    String password;
    String email;
    LocalDate birthday;
    Role role;
    Gender gender;
    LocalDateTime created_at;
}
