package com.library.users.business.service;

import com.library.users.business.dao.UserDao;
import com.library.users.business.mapper.UserMapper;
import com.library.users.business.service.client.RentalsFeignClient;
import com.library.users.controller.dto.RentalDto;
import com.library.users.controller.dto.UserDetailsDto;
import com.library.users.controller.dto.UserDto;
import com.library.users.domain.NotFoundException;
import com.library.users.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class UserService {
    private UserDao userDao;
    private UserMapper mapper;

    private RentalsFeignClient rentalsFeignClient;

    public UserDetailsDto getUserDetails(String phoneNumber){
        User user=userDao.getUserByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", phoneNumber)));
        ResponseEntity<List<RentalDto>> rentals= rentalsFeignClient.fetchUserRentals(user.getId());
        log.info("Fetching user details");
        return UserDetailsDto.builder()
                .user(mapper.map(user))
                .rentals(rentals.getBody())
                .build();
    }

    public UserDto createUser(UserDto userDto) {
        return mapper.map(userDao.createUser(mapper.map(userDto)));
    }
    public void deleteUserByPhoneNumber(String phoneNumber) {
        userDao.deleteUserByPhoneNumber(phoneNumber);
    }
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        User user = userDao.getUserByPhoneNumber(phoneNumber).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", phoneNumber)));
        return mapper.map(user);
    }
    public List<UserDto> getUsers() {
        return userDao.getUsers().stream().map(mapper::map).toList();
    }
    public UserDto updateUser(UserDto userDto) {
        User user = userDao.getUserByPhoneNumber(userDto.getPhoneNumber()).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", userDto.getPhoneNumber())));
        return mapper.map(userDao.updateUser(mapper.map(userDto)));
    }
}
