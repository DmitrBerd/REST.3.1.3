package com.example.kata.springboot.service;

import com.example.kata.springboot.dao.UserDao;
import com.example.kata.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void remove(long id) {
        userDao.remove(id);
    }

    @Override
    public void edit(User user) {
        userDao.edit(user);
    }

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByEmail(String name) {
        return userDao.getUserByEmail(name);
    }

    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }
}
