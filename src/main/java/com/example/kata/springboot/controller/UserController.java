package com.example.kata.springboot.controller;

import com.example.kata.springboot.model.Role;
import com.example.kata.springboot.model.User;
import com.example.kata.springboot.service.RoleService;
import com.example.kata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String listUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("allUsers", userService.allUser());
        return "admin";
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }
}
