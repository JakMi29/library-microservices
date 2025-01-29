package com.library.users.infrastructure.database.repository.jpa;

import com.library.users.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserJpaRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);
}
