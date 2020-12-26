package ru.daniels.instaclone.dao;

import ru.daniels.instaclone.model.dbentity.DBEntity;
import ru.daniels.instaclone.model.dbentity.Post;
import ru.daniels.instaclone.model.dbentity.Tag;
import ru.daniels.instaclone.model.dbentity.User;

import java.util.List;

public interface Dao<T extends DBEntity> {
    T create(T entity);
    T read(long id, Class<T> entity);
    T readByName(String columnName, String value, Class<T> entity);
    User update(long id);
    void delete(long id, Class<T> entity);
    List<Post> findPostsByUser(long id);
    void addNewRelationPostTag(Tag tag, Post post);

}
