package ru.daniels.instaclone.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.model.dbentity.User;
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

    @PostMapping(path = "/register_user")
    public @ResponseBody String register(@RequestBody User user) {
        User newUser = service.createUser(user);
        if(newUser != null) {
            return "/"+ newUser.getNickname() + "?id=" + newUser.getId();
        }
        throw new IllegalArgumentException("exception_reg");
    }
}
