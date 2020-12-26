package ru.daniels.instaclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.daniels.instaclone.exceptions.AuthorizationException;
import ru.daniels.instaclone.model.dbentity.User;
import ru.daniels.instaclone.service.UserService;

@Controller
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

    @PostMapping(path = "/register_user")
    public @ResponseBody String register(@RequestBody User user) {
        User newUser = service.createUser(user);
        if(newUser != null) {
            return "/"+ newUser.getNickname() + "?id=" + newUser.getId();
        }
        throw new AuthorizationException("exception_reg");
    }
}
