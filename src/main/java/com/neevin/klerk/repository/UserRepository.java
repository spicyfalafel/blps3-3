package com.neevin.klerk.repository;

import com.neevin.klerk.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void save(User user);

    Optional<User> findById(Integer id);

    List<User> getAllUsers();

}
