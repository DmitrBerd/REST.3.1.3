package com.example.kata.springboot.dao;
import com.example.kata.springboot.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void remove(long id);

    void edit(User user);

    User getById(long id);

    List<User> allUser();
    User getUserByName(String name);
}
