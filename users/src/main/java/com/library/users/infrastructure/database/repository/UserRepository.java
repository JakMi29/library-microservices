package com.library.users.infrastructure.database.repository;

import com.library.users.business.dao.UserDao;
import com.library.users.domain.User;
import com.library.users.infrastructure.database.repository.jpa.UserJpaRepository;
import com.library.users.infrastructure.database.repository.mapper.UserEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserRepository implements UserDao {

    private final UserJpaRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public User createUser(User user) {
        return mapper.map(repository.save(mapper.map(user)));
    }

    @Override
    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber).map(mapper::map);
    }

    @Override
    public void deleteUserByPhoneNumber(String phoneNumber) {
        repository.deleteByPhoneNumber(phoneNumber);
    }

    @Override
    public User updateUser(User user) {
        return mapper.map(repository.save(mapper.map(user)));
    }

    @Override
    public List<User> getUsers() {
        return repository.findAll().stream().map(mapper::map).toList();
    }
}
