package ru.daniels.instaclone.api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.daniels.instaclone.api.configuration.Constants;
import ru.daniels.instaclone.api.model.Profile;
import ru.daniels.instaclone.api.security.AuthRequest;
import ru.daniels.instaclone.api.security.AuthResponse;
import ru.daniels.instaclone.api.security.JWTUtils;
import ru.daniels.instaclone.api.security.SecUser;


@RestController
@RequestMapping("/api")
public class SecController {
    private final Logger logger = LoggerFactory.getLogger(SecController.class);

    private final AuthenticationManager manager;

    private final JWTUtils jwtTokenUtils;

    @Autowired
    public SecController(AuthenticationManager manager, JWTUtils jwtTokenUtils) {
        this.manager = manager;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private AuthResponse createAuthToken(@RequestBody AuthRequest request){
        Authentication authentication;
        logger.info("start authentication user: {}", request);
        try{
            authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));
            logger.info("authenticated: {}", authentication.getPrincipal());
        }catch (BadCredentialsException ex){
            logger.error("Wrong login or password: {}", request);
            throw new IllegalArgumentException("Wrong login or password");
        }
        String jwt = jwtTokenUtils.getToken((UserDetails) authentication.getPrincipal());
        logger.info("jwt token: {}", jwt);
        SecUser authUser = (SecUser) authentication.getPrincipal();
        return new AuthResponse(jwt, createProfile(authUser));
    }

    public static SecUser getAuthUser(){
        return (SecUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    private Profile createProfile(SecUser user){
        return Profile
                .builder()
                    .setId(user.getId())
                    .setEmail(user.getEmail())
                    .setNickname(user.getNickname())
                    .setFirstName(user.getFirstName())
                    .setLastName(user.getLastName())
                    .setAvatar("/media/" + Constants.IMAGES_FOLDER + user.getAvatar().getImageURL())
                    .setPhone(user.getPhone())
                .build();
    }
}
