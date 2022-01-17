package com.example.kata.springboot.service;

import com.example.kata.springboot.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void remove(long id);

    void edit(User user);

    User getById(long id);

    User getUserByEmail(String name);

    List<User> allUser();
}
