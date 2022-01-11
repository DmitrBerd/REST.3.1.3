package com.example.kata.springboot.dao;



import com.example.kata.springboot.model.Role;

import java.util.List;

public interface RoleDao {

    void addRole(Role role);

    void removeRoleByID(long id);

    void editRole(Role role);

    List<Role> getAllRoles();

    Role getRole(String role);
}
