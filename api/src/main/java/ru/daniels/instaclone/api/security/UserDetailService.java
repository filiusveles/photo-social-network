package ru.daniels.instaclone.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.daniels.instaclone.api.dao.Dao;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {

    private final Dao dao;

    public UserDetailService(Dao dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SecUser currentUser = (SecUser)dao.readByName("email", username, SecUser.class);
        if(currentUser == null) {
            throw new UsernameNotFoundException(
                    String.format("User with email \\\" %s\\\" not found", username));
        }
        currentUser.setRoles(Collections.singleton(Role.USER));
        return currentUser;
    }
}
