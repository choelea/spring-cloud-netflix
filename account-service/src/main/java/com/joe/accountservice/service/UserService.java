package com.joe.accountservice.service;

import java.util.List;

import com.joe.accountservice.model.User;

public interface UserService {
    void save(User user);
    User createBuyer(User user);
    User findByUsername(String username);
    List<User> find();
    /**
     * Return user if find an user match the give username and password
     * @param username
     * @param password
     * @return
     */
    User get(String username,String password);
}
