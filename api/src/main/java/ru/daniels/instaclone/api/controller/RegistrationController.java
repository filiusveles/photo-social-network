package ru.daniels.instaclone.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.configuration.Constants;
import ru.daniels.instaclone.api.model.RegRequest;
import ru.daniels.instaclone.api.security.Role;
import ru.daniels.instaclone.api.security.SecUser;
import ru.daniels.instaclone.api.service.UserService;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class RegistrationController {

    private UserService service;
    private final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

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
     * @param regNewUser
     * @return
     */
    @PostMapping(path = "/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody RegRequest regNewUser) {
        LOGGER.info("Get new user's data: {}", regNewUser);
        if(service.findByNickname(regNewUser.getNickname()) != null){
            LOGGER.error("found user with same nickname: {}", service.findByNickname(regNewUser.getNickname()));
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(service.findByEmail(regNewUser.getEmail()) != null){
            LOGGER.error("found user with same email: {}", service.findByEmail(regNewUser.getEmail()));
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        LOGGER.info("Start save user: {}", regNewUser);
        SecUser user = new SecUser();
        user.setNickname(regNewUser.getNickname());
        user.setFirstName(regNewUser.getFirstName());
        user.setLastName(regNewUser.getLastName());
        user.setEmail(regNewUser.getEmail());
        user.setPhone(regNewUser.getPhone());
        user.setPassword(regNewUser.getPassword());
        user.setPosts(new ArrayList<>());
        user.setRoles(Collections.singleton(Role.USER));
        user.setAvatar(service.getImage(Constants.DEFAULT_IMAGE_ID));
        service.createUser(user);
        LOGGER.info("User saved: {}", user);
        Constants.createUserDir(user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
