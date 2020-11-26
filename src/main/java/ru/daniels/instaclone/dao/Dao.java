package ru.daniels.instaclone.dao;

import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.User;

import java.util.List;

public interface Dao {
    User readById(long id);
    User readByName(String columnName, String value);
    User update(long id);
    long create(User user);
    void delete(long id);

    List<Post> findPostsByUser(long id);
    long createPost(Post post);
}
