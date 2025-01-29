package com.library.users.business.mapper;

import com.library.users.domain.User;
import com.library.users.controller.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .surname(userDto.getSurname())
                .name(userDto.getName())

                .build();
    }

    public UserDto map(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .surname(user.getSurname())
                .name(user.getName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
