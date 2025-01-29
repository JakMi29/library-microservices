package com.library.users.business.dao;


import com.library.users.domain.User;

import java.util.Optional;

public interface UserDao {

    User createUser(User user);

    Optional<User> getUserByPhoneNumber(String phoneNumber);

    void deleteUserByPhoneNumber(String phoneNumber);

    User updateUser(User user);
}
