package ru.daniels.instaclone.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.configuration.Constants;
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

    private static final Logger lOGGER = LoggerFactory.getLogger(CreateDataController.class);

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping(path="/{nickname}/create_post")
    private ResponseEntity<HttpStatus> createPost(@PathVariable("nickname") String nickname, @RequestBody PostView newPost){
        SecUser currentUser = SecController.getAuthUser();
        if(!currentUser.getNickname().equals(nickname)) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        lOGGER.info("Start save: {}'s post {}", currentUser, newPost);
        Image image = new Image();
        image.setImageURL(Constants.IMAGES_FOLDER +
                currentUser.getId() +
                "/" +
                Constants.convertAndGetImageName(newPost.getImage())
        );
        lOGGER.info("Image path after convert to md5: {}", image.getImageURL());
        Post post = new Post();
        post.setAuthor(currentUser);
        post.setDate(new Date(new java.util.Date().getTime()));
        post.setTitle(newPost.getTitle());
        post.setImage(service.createImage(image));
        post.setDescription(newPost.getDescription());
        post.setTags(new ArrayList<>());
        if(service.createPost(post) == null){
            new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        lOGGER.info("Created post: {}", post);
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
