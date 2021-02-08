package ru.daniels.instaclone.api.service;

import ru.daniels.instaclone.api.model.PostView;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.model.dbentity.Tag;
import ru.daniels.instaclone.api.model.dbentity.User;
import ru.daniels.instaclone.api.security.SecUser;

import java.util.List;

public interface UserService {


    User findById(long id);

    User findByNickname(String nickname);

    SecUser findByEmail(String email);

    SecUser createUser(SecUser user);

/*    User updateUser(long id);

    void deleteUser(long id);*/

    List<PostView> getUserPosts(long id);

    Post createPost(Post post);

    PostView getPost(long id);

    Image createImage(Image image);

    Image getImage(Long id);

    void createTag(long postId, String tag);

    Tag getTagByName(String name);

    List<PostView> getPostsByTagName(String tagName);
}
