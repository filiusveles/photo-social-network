package ru.daniels.instaclone.service;

import ru.daniels.instaclone.model.User;

public interface UserService {

    Long userAuthorization(User user);

    User findById(long id);

    User register(User user);

    User updateUser(long id);

    void deleteUser(long id);
}
