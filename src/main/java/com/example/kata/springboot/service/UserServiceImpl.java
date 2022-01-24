package com.example.kata.springboot.service;

import com.example.kata.springboot.repository.UserRepository;
import com.example.kata.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void remove(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {

        return userRepository.getUserByEmail(email);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> allUser() {
        return userRepository.findAll();
    }
}
