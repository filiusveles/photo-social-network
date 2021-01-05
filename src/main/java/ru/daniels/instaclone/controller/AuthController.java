package ru.daniels.instaclone.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.exceptions.AuthorizationException;
import ru.daniels.instaclone.model.dbentity.User;
import ru.daniels.instaclone.service.UserService;

@Controller
public class AuthController {

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping(path="/login")
    public @ResponseBody String logIn(@RequestBody User user, Model model){
        Long id = service.userAuthorization(user);
        if(id > -1) {
            user = service.findById(id);
            model.addAttribute(user);
            return "/"+ user.getNickname() + "?id=" + user.getId();
        }
        throw new AuthorizationException("exception_login");
    }
}
