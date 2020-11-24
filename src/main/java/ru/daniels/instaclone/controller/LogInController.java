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

    @PostMapping(path="/login")
    public User logIn(@RequestBody User user){
        Long id = service.userAuthorization(user);
        if(id > 0) return service.findById(id);
        return user;
    }
}
