package ru.daniels.instaclone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.model.PostView;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.security.SecUser;
import ru.daniels.instaclone.api.service.UserService;

import java.sql.Date;
import java.util.ArrayList;


@RestController
public class CreateDataController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping(path="/{nickname}/create_post")
    private ResponseEntity<HttpStatus> createPost(@PathVariable("nickname") String nickname, @RequestBody PostView newPost){
        SecUser currentUser = SecController.getAuthUser();
        if(!currentUser.getNickname().equals(nickname)) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Image image = new Image();
        image.setImage(newPost.getImage());
        Post post = new Post();
        post.setAuthor(currentUser);
        post.setDate(new Date(new java.util.Date().getTime()));
        post.setTitle(newPost.getTitle());
        post.setResultImage(service.createImage(image));
        post.setDescription(newPost.getDescription());
        post.setTags(new ArrayList<>());

        if(service.createPost(post) == null){
            new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(path = "post/{id}/add_tag")
    private ResponseEntity<HttpStatus> addTag(@PathVariable("id")long id, @RequestBody String tag){
        SecUser currentUser = SecController.getAuthUser();
        PostView post = service.getPost(id);
        if(!currentUser.getNickname().equals(post.getAuthor())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        service.createTag(id, tag);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
