package ru.daniels.instaclone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.model.UserPageView;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.security.SecUser;
import ru.daniels.instaclone.api.service.UserService;

@RestController
public class RegistrationController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "reg";
    }

    /**
     * Создает папку пользователя автоматически, при успешной регистрации
     * @param newUser
     * @return
     */
    @PostMapping(path = "/register_user")
    public @ResponseBody String register(@RequestBody UserPageView newUser) {
        SecUser user = new SecUser();
        user.setNickname(newUser.getNickname());
        Image image = new Image();
        user.setAvatar(null);

        //User newUser = service.createUser(user);
        if(newUser != null) {
           return "/"+ newUser.getNickname() + "?id=";
        }
        throw new IllegalArgumentException("exception_reg");
    }
}
