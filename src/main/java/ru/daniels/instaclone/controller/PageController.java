package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.model.UserPage;
import ru.daniels.instaclone.service.UserService;

@Controller
public class PageController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "reg";
    }

    @GetMapping("/{nickname}")
    public String homePage(@PathVariable("nickname") String nickname, Model model){
        User user = service.findByNickname(nickname);
        UserPage userPage = new UserPage();
        userPage.setUserId(user.getId());
        userPage.setAvatar(user.getAvatar().getImage());
        userPage.setNickname(user.getNickname());
        userPage.setPosts(service.getUserPosts(user.getId()));
        model.addAttribute(userPage);
        return "welcome";
    }

    @GetMapping("/post/{id}")
    private String post(@PathVariable("id") long id, Model model){
        Post post = service.getPost(id);
        model.addAttribute(post);
        return "post";
    }

}
