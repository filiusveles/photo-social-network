package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.daniels.instaclone.model.*;
import ru.daniels.instaclone.service.UserService;

import java.util.ArrayList;
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
    public String homePage(@PathVariable("nickname") String nickname, @RequestParam(value = "id", required = false) Long userId, Model model){
        User user = service.findByNickname(nickname);
        UserPage userPage = new UserPage();
        userPage.setUserId(user.getId());
        userPage.setAvatar(user.getAvatar().getImage());
        userPage.setNickname(user.getNickname());
        userPage.setPosts(service.getUserPosts(user.getId()));
        AuthorizedUser authorizedUser = getAuthorizedUser(userId);
        model.addAttribute("userPage", userPage);
        model.addAttribute("user", authorizedUser);
        return "welcome";
    }

    @GetMapping("/post/{postId}")
    private String post(@PathVariable("postId") long postId, @RequestParam(value = "id", required = false) Long userId, Model model){
        Post post = service.getPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("user", getAuthorizedUser(userId));
        return "post";
    }

    @GetMapping("/search")
    private String searchPostByTag(@RequestParam("tag") String tagName, @RequestParam(value = "user_id", required = false) Long id, Model model){
        Tag tag = service.getTagByName(tagName);
        if(tag == null){
            tag = new Tag();
            tag.setPosts(new ArrayList<>());
        }
        model.addAttribute("tag",tag);
        model.addAttribute("user",getAuthorizedUser(id));
        return "search_page";
    }

    /**
     * @param id полученный id или null
     * @return возвращает либо действительного пользователя после авторизации либо "гость"
     */
    private AuthorizedUser getAuthorizedUser(Long id){
        AuthorizedUser authorizedUser = new AuthorizedUser();
        if(id != null) {
            User user = service.findById(id);
            authorizedUser.setId(user.getId());
            authorizedUser.setNickname(user.getNickname());
        }else{
            authorizedUser.setId(null);
            authorizedUser.setNickname("Гость");
        }
        return authorizedUser;
    }

}
