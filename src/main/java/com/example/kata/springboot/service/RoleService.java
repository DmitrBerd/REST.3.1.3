package com.example.kata.springboot.service;

import com.example.kata.springboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRole(String role);
}
