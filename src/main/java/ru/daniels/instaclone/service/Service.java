package ru.daniels.instaclone.service;

import ru.daniels.instaclone.model.User;
import ru.daniels.instaclone.model.UserAuthorization;

public interface Service {
    void authorization(UserAuthorization authorization);

    long getUser(long id);

    long createUser(User user);

    void updateUser(long id);

    void deleteUser(User user);
}
