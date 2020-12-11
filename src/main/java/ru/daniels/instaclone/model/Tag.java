package ru.daniels.instaclone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="tag", schema = "data")
public class Tag {
    private long id;
    @JsonProperty("tag")
    private String name;
    private Set<Post> posts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    public long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @JoinTable(name="tagmap",
            schema = "data",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "tag_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "post_id",
                    referencedColumnName = "id")
    )
    @ManyToMany
    public Set<Post> getPosts() {
        return posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
