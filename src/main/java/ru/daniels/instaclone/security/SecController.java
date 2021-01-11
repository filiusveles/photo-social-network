package ru.daniels.instaclone.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api")
public class SecController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JWTUtils jwtTokenUtils;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    private AuthResponse createAuthToken(@RequestBody AuthRequest request){
        Authentication authentication;
        try{
            authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));
            System.out.println(authentication);
        }catch (BadCredentialsException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong login or password", ex);
        }
        String jwt = jwtTokenUtils.getToken((UserDetails) authentication.getPrincipal());
        return new AuthResponse(jwt);
    }


}
