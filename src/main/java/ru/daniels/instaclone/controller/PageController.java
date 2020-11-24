package ru.daniels.instaclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcome")
public class PageController {
    @GetMapping
    public String loginPage(){
        return "welcome";
    }
}
