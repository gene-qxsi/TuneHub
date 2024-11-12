package com.matrosov.mapper;

import com.matrosov.dto.UserDto;
import com.matrosov.entity.User;

public class UserDtoMapper implements Mapper<User, UserDto> {

    @Override
    public UserDto map(User entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .birthday(entity.getBirthday())
                .role(entity.getRole())
                .gender(entity.getGender())
                .created_at(entity.getCreated_at())
                .build();
    }
}
