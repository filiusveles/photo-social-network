package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.model.dbentity.Image;
import ru.daniels.instaclone.model.dbentity.Post;
import ru.daniels.instaclone.model.dbentity.Tag;
import ru.daniels.instaclone.model.dbentity.User;
import ru.daniels.instaclone.service.UserService;

import java.sql.Date;


@RestController
public class CreateDataController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
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
