package ru.daniels.instaclone.api.model;


import ru.daniels.instaclone.api.model.dbentity.Post;

import java.util.List;

public class UserPageView {

    private long userId;
    private String nickname;
    private String avatar;
    private List<PostView> posts;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<PostView> getPosts() {
        return posts;
    }

    public void setPosts(List<PostView> posts) {
        this.posts = posts;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
