package ru.daniels.instaclone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.daniels.instaclone.dao.Dao;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private Dao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecUser currentUser = (SecUser)dao.readByName("email", username, SecUser.class);
        if(currentUser == null) {
            throw new UsernameNotFoundException(
                    String.format("User with email \\\" %s\\\" not found", username));
        }
        UserDetails user = User.builder()
                .username(currentUser.getEmail())
                .password(currentUser.getPassword())
                .roles(Role.USER.getRole())
                .build();
        return user;
    }
}
