package ru.daniels.instaclone.dao;

import ru.daniels.instaclone.model.User;

public interface Dao {
    User readById(long id);
    User readByName(String name);
    User update(long id);
    long create(User user);
    void delete(long id);
}
