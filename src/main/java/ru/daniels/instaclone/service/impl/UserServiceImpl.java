package ru.daniels.instaclone.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.daniels.instaclone.dao.Dao;
import ru.daniels.instaclone.model.dbentity.Image;
import ru.daniels.instaclone.model.dbentity.Post;
import ru.daniels.instaclone.model.dbentity.Tag;
import ru.daniels.instaclone.model.dbentity.User;
import ru.daniels.instaclone.service.UserService;

import java.util.ArrayList;
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
        return (User) dao.read(id, User.class);
    }

    @Override
    public User findByNickname(String nickname) {
        User user = dao.readByName("nickname", nickname);
        if(user == null) return new User();
        return user;
    }

/*    @Override
    public User updateUser(long id) {
        return null;
    }

    @Override
    public void deleteUser(long id) {

    }*/

    @Override
    public List<Post> getUserPosts(long id) {
        return dao.findPostsByUser(id);
    }

    @Override
    public User createUser(User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        return (User) dao.create(user);
    }

    @Override
    public Post createPost(Post post) {
        post.setTags(new ArrayList<>());
        return (Post) dao.create(post);
    }

    @Override
    public Image createImage(Image image) {
        return (Image) dao.create(image);
    }

    @Override
    public void createTag(long postId, Tag tag) {
        Tag findTag = dao.findTagByName(tag.getName());
        Post post = getPost(postId);
        if(findTag != null){
            post.getTags().add(findTag);
            dao.addNewRelationPostTag(findTag, post);
        }else{
            tag.setPosts(new ArrayList<>());
            tag.getPosts().add(post);
            dao.create(tag);
        }
    }

    @Override
    public Post getPost(long id) {
        return (Post) dao.read(id, Post.class);
    }

    @Override
    public Tag getTagByName(String name) {
        return dao.findTagByName(name);
    }

    @Override
    public List<Post> getPostsByTagName(String tagName) {
        Tag tag = dao.findTagByName(tagName);
        if(tag == null) return new ArrayList<>();
        return tag.getPosts();
    }
}
