package ru.daniels.instaclone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.model.UserAuthorization;


@Controller
@RequestMapping("/welcome")
public class LogInController {


    @GetMapping
    public String loginPage(){
        return "welcome";
    }

    @PostMapping(path="/login")
    public String logIn(@RequestBody UserAuthorization authorization){
        System.out.printf("login:%s password:%s\n", authorization.getLogin(), authorization.getPassword());
        return "test";
    }
}
