package ru.daniels.instaclone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.daniels.instaclone.api.model.*;
import ru.daniels.instaclone.api.model.dbentity.User;
import ru.daniels.instaclone.api.service.UserService;
import java.util.List;

@RestController
public class PageController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/{nickname}")
    public ResponseEntity <UserPageView>homePage(@PathVariable("nickname") String nickname){
        User user = service.findByNickname(nickname);
        UserPageView userPageView = new UserPageView();
        userPageView.setUserId(user.getId());
        userPageView.setAvatar(user.getAvatar().getImage());
        userPageView.setNickname(user.getNickname());
        userPageView.setPosts(service.getUserPosts(user.getId()));
        return new ResponseEntity<>(userPageView, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    private ResponseEntity<PostView> post(@PathVariable("postId") long postId){
        PostView post = service.getPost(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/search")
    private ResponseEntity<List<PostView>> searchPostByTag(@RequestParam("tag") String tagName){
        List<PostView> postViewList = service.getPostsByTagName(tagName);
        return new ResponseEntity<>(postViewList, HttpStatus.OK);
    }
}
