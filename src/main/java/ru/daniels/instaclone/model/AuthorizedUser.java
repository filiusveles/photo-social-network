package ru.daniels.instaclone.model;

import javax.persistence.Entity;

@Entity
public class AuthorizedUser {
    private Long id;
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
