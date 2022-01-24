package com.example.kata.springboot.controller;

import com.example.kata.springboot.model.Role;
import com.example.kata.springboot.model.User;
import com.example.kata.springboot.service.RoleService;
import com.example.kata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserRestController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.allUser();
        ResponseEntity responseEntity;
        if (!users.isEmpty()) {
            responseEntity = new ResponseEntity(users, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("admin/authorities")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        ResponseEntity responseEntity;
        if (!roles.isEmpty()) {
            responseEntity = new ResponseEntity(roles, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("admin/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.getById(id);
        ResponseEntity responseEntity;
        if (user != null) {
            responseEntity = new ResponseEntity(user, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PostMapping("admin")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("admin")
    public ResponseEntity<User> editUser(@RequestBody User user) {
        userService.edit(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("admin/{id}")
    public ResponseEntity<User> removeUser(@PathVariable("id") long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
