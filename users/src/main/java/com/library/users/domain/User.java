package com.library.users.domain;

import lombok.*;

import java.time.LocalDateTime;

@Value
@With
@Builder
@EqualsAndHashCode
public class User {
    String email;
    String phoneNumber;
    String name;
    String surname;
    Integer id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
