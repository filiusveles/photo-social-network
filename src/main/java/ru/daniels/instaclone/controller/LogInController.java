package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.exceptions.AuthorizationException;
import ru.daniels.instaclone.model.Image;
import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.model.UserPage;
import ru.daniels.instaclone.service.UserService;

import java.sql.Date;


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

    @PostMapping(path="/{id}/create_post")
    private String createPost(@PathVariable("id") long id, @RequestBody Post post){

        System.out.println(post);

        User user = service.findById(id);
        Image image = new Image();
        image.setImage(post.getBase64image());
        post.setResultImage(service.createImage(image));
        post.setAuthor(user);
        post.setDate(new Date(new java.util.Date().getTime()));

        System.out.println(post);

        Post savedPost = service.createPost(post);

        System.out.println(savedPost);

        if(savedPost != null) return "ok";
        throw new IllegalArgumentException("create post exception");
    }

    //тестовый метод
    @PostMapping(path = "/register_user")
    public UserPage register(@RequestBody User user) {
        User newUser = service.register(user);
        if(newUser != null) {
            UserPage userPage = new UserPage();
            userPage.setAvatar(newUser.getAvatar().getImage());
            userPage.setNickname(newUser.getNickname());
            userPage.setPosts(null);
            return userPage;
        }
        throw new AuthorizationException("exception_reg");
    }

}
