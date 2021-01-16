package ru.daniels.instaclone.api.service.impl;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.daniels.instaclone.api.dao.Dao;
import ru.daniels.instaclone.api.model.PostView;
import ru.daniels.instaclone.api.model.dbentity.Image;
import ru.daniels.instaclone.api.model.dbentity.Post;
import ru.daniels.instaclone.api.model.dbentity.Tag;
import ru.daniels.instaclone.api.model.dbentity.User;
import ru.daniels.instaclone.api.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("service")
public class UserServiceImpl implements UserService {
    private final Dao dao;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(Dao dao, BCryptPasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long userAuthorization(User user) {
        User getUser = (User) dao.readByName("email", user.getEmail(), User.class);
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
        User user = (User) dao.readByName("nickname", nickname, User.class);
        if(user == null) return new User();
        return user;
    }

    @Override
    public List<PostView> getUserPosts(long id) {
        List<Post> posts = dao.findPostsByUser(id);
        if(posts == null) return new ArrayList<>();
        return convertPostListToPostViewsList(posts);
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
        Tag findTag = getTagByName(tag.getName());
        Post post = (Post) dao.read(postId, Post.class);
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
    public PostView getPost(long id) {
        Post post = (Post) dao.read(id, Post.class);
        if(post == null) return PostView.builder().build();
        return convertPostToPostView(post);
    }

    @Override
    public List<PostView> getPostsByTagName(String tagName) {
        Tag tag = getTagByName(tagName);
        if(tag == null) return new ArrayList<>();
        return convertPostListToPostViewsList(tag.getPosts());
    }

    @Override
    public Tag getTagByName(String name) {
        return (Tag) dao.readByName("name", name, Tag.class);
    }


    private List<PostView> convertPostListToPostViewsList(List<Post> posts){
        List<PostView> postViews = new ArrayList<>();
        posts.forEach(post -> {
            postViews.add(convertPostToPostView(post));
        });
        return postViews;
    }

    private PostView convertPostToPostView(Post post){
        return PostView
                .builder()
                    .setImage(post.getResultImage().getImage())
                    .setAuthor(post.getAuthor().getNickname())
                    .setDescription(post.getDescription())
                    .setTags(post.getTags()
                            .stream()
                            .map(Tag::getName)
                            .collect(Collectors.toList()))
                .build();
    }
}
