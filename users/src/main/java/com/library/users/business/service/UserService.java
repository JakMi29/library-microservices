package com.library.users.business.service;

import com.library.users.business.dao.UserDao;
import com.library.users.business.mapper.UserMapper;
import com.library.users.controller.dto.UserDto;
import com.library.users.domain.NotFoundException;
import com.library.users.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    private UserMapper mapper;

    @Transactional
    public UserDto createUser(UserDto userDto) {
        return mapper.map(userDao.createUser(mapper.map(userDto)));
    }
    @Transactional
    public void deleteUserByPhoneNumber(String phoneNumber) {
        userDao.deleteUserByPhoneNumber(phoneNumber);
    }
    @Transactional
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        User user = userDao.getUserByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", phoneNumber)));
        return mapper.map(user);
    }
    @Transactional
    public UserDto updateUser(UserDto userDto) {
        User user = userDao.getUserByPhoneNumber(userDto.getPhoneNumber()).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", userDto.getPhoneNumber())));
        return mapper.map(userDao.updateUser(mapper.map(userDto)));
    }
}
