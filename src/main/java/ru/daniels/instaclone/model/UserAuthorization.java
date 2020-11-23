package ru.daniels.instaclone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name="login_data", schema="authentication")
public class UserAuthorization {

    @JsonProperty("username")
    private String login;
    @JsonProperty("password")
    private String password;

    private long userId;

    @Id
    @Column(name="user_id")
    public long getUserId(){
        return userId;
    }

    @Column(name ="login", nullable = false)
    public String getLogin() {
        return login;
    }

    @Column(name ="password")
    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
