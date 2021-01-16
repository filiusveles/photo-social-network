package ru.daniels.instaclone.api.service;

import ru.daniels.instaclone.api.model.PostView;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.model.dbentity.Tag;
import ru.daniels.instaclone.api.model.dbentity.User;

import java.util.List;

public interface UserService {

    Long userAuthorization(User user);

    User findById(long id);

    User findByNickname(String nickname);

    User createUser(User user);

/*    User updateUser(long id);

    void deleteUser(long id);*/

    List<PostView> getUserPosts(long id);

    Post createPost(Post post);

    PostView getPost(long id);

    Image createImage(Image image);

    void createTag(long postId, Tag tag);

    Tag getTagByName(String name);

    List<PostView> getPostsByTagName(String tagName);




}
