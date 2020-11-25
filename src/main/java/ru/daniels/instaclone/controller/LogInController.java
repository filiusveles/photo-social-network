package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.service.UserService;


@RestController
public class LogInController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping(path="/log_in")
    public String logIn(@RequestBody User user){
        Long id = service.userAuthorization(user);
        if(id > -1) return "/welcome";
        return "/login";
    }

    @PostMapping(path = "/register_user")
    public String register(@RequestBody User user){
        User newUser = service.register(user);
        if(newUser != null) return "/welcome";
        return "/registration";
    }
}
