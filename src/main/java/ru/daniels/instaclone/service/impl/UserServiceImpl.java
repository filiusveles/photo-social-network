package ru.daniels.instaclone.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.daniels.instaclone.dao.Dao;
import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.service.UserService;

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
    @Transactional
    public Long userAuthorization(User user) {
        User getUser = dao.readByName(user.getEmail());
        String password = passwordEncoder.encode(user.getPassword());
        System.out.printf("%s = %s", user.getPassword(), password);
        if(getUser != null && passwordEncoder.matches(user.getPassword(), getUser.getPassword()))
        {
            System.out.println();
            return getUser.getId();
        }
        return -1L;
    }

    @Override
    public User findById(long id) {
        return dao.readById(id);
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return null;
    }

    @Override
    public User updateUser(long id) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }
}
