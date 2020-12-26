package ru.daniels.instaclone.model.dbentity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="tag", schema = "data")
public class Tag implements DBEntity {
    private long id;
    @JsonProperty("tag")
    private String name;
    private List<Post> posts;

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
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Post> getPosts() {
        return posts;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}
