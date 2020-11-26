package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.exceptions.AuthorizationException;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.model.UserPage;
import ru.daniels.instaclone.service.UserService;


@RestController
public class LogInController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
/*
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
    }*/

    @PostMapping(path="/login")
    public String logIn(@RequestBody User user){
        Long id = service.userAuthorization(user);
        if(id > -1) {
            user = service.findById(id);
            return "/"+ user.getNickname();
        }
        throw new AuthorizationException("exception_login");
    }

    //тестовый метод
    @PostMapping(path = "/register_user")
    public UserPage register(@RequestBody User user) {
        User newUser = service.register(user);
        if(newUser != null) {
            UserPage userPage = new UserPage();
            userPage.setAvatar(newUser.getAvatarUrl());
            userPage.setNickname(newUser.getNickname());
            userPage.setPageUrl("/{nickname}");
            userPage.setPosts(null);
            return userPage;
        }
        throw new AuthorizationException("exception_reg");
    }

}
