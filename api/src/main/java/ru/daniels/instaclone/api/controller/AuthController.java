package ru.daniels.instaclone.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.daniels.instaclone.api.service.UserService;

@RestController
public class AuthController {
    private UserService service;
    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping("/convert")
    public HttpStatus loginPage(){
        return HttpStatus.OK;
    }
}
