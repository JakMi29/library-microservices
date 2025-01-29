package com.library.users.infrastructure.database.repository.mapper;

import com.library.users.domain.User;
import com.library.users.infrastructure.database.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {
    public User map(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .surname(entity.getSurname())
                .name(entity.getName())
                .updatedAt(entity.getUpdatedAt())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public UserEntity map(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .surname(user.getSurname())
                .name(user.getName())
                .build();
    }
}
