package ru.daniels.instaclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(){
        return "reg";
    }

    @GetMapping("/{nickname}")
    public String homePage(){
        return "welcome";
    }
}
