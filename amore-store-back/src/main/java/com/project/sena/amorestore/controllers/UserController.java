package com.project.sena.amorestore.controllers;

import com.project.sena.amorestore.models.User;
import com.project.sena.amorestore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/all")
    public List<User> findAll() {
        return userService.findAll();
    }


}
