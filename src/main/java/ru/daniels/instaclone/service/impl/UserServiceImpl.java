package ru.daniels.instaclone.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.daniels.instaclone.dao.Dao;
import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private Dao dao;
    private BCryptPasswordEncoder passwordEncoder;


    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long userAuthorization(User user) {
        User getUser = dao.readByName("email", user.getEmail());
        if(getUser != null && passwordEncoder.matches(user.getPassword(), getUser.getPassword()))
        {
            return getUser.getId();
        }
        return -1L;
    }

    @Override
    public User findById(long id) {
        return dao.readById(id);
    }

    @Override
    public User findByNickname(String nickname) {
        User user = dao.readByName("nickname", nickname);
        if(user == null) return new User();
        return user;
    }



    @Override
    public User register(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        long id = dao.create(user);
        return findById(id);
    }

    @Override
    public User updateUser(long id) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }

    @Override
    public List<Post> getUserPosts(long id) {
        return dao.findPostsByUser(id);
    }

    @Override
    public Post createPost(Post post) {
        return null;
    }
}
