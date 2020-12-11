package ru.daniels.instaclone.service;

import ru.daniels.instaclone.model.Image;
import ru.daniels.instaclone.model.Post;
import ru.daniels.instaclone.model.Tag;
import ru.daniels.instaclone.model.User;

import java.util.List;

public interface UserService {

    Long userAuthorization(User user);

    User findById(long id);

    User findByNickname(String nickname);

    User register(User user);

    User updateUser(long id);

    void deleteUser(long id);

    List<Post> getUserPosts(long id);

    Post createPost(Post post);

    Post getPost(long id);

    Image createImage(Image image);

    void createTag(long postId, Tag tag);


}
