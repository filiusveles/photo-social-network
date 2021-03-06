package ru.daniels.instaclone.api.model.dbentity;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users", schema = "data")
public class User implements DBEntity {
    @JsonProperty("id")
    private long id;
    @JsonProperty("firstname")
    private String firstName;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("avatar")
    private Image avatar;

    private Collection<Post> posts;

    @Id
    @SequenceGenerator(name="user_seq", sequenceName = "data.users_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "id")
    public long getId(){
        return id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    @OneToOne
    @JoinColumn(name = "avatar")
    public Image getAvatar() {
        return avatar;
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    public Collection<Post> getPosts() {
        return posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                '}';
    }
}
