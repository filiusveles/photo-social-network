package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.exceptions.AuthorizationException;
import ru.daniels.instaclone.model.*;
import ru.daniels.instaclone.service.UserService;

import java.sql.Date;


@RestController
public class LogInController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

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
            userPage.setAvatar(newUser.getAvatar().getImage());
            userPage.setNickname(newUser.getNickname());
            userPage.setPosts(null);
            return userPage;
        }
        throw new AuthorizationException("exception_reg");
    }

    @PostMapping(path="/{id}/create_post")
    private String createPost(@PathVariable("id") long id, @RequestBody Post post){
        User user = service.findById(id);
        Image image = new Image();
        image.setImage(post.getBase64image());
        post.setResultImage(service.createImage(image));
        post.setAuthor(user);
        post.setDate(new Date(new java.util.Date().getTime()));
        Post savedPost = service.createPost(post);
        if(savedPost != null) return "ok";
        throw new IllegalArgumentException("create post exception");
    }

    @PostMapping(path = "post/{id}/add_tag")
    private String addTag(@PathVariable("id")long id, @RequestBody Tag tag){
        service.createTag(id, tag);
        return "ok";
    }



}
