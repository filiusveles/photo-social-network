package ru.daniels.instaclone.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="posts", schema = "data")
public class Post {


    private long id;
    private long author;
    private String imageUrl;
    private Date date;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @Column(name = "author_id")
    public long getAuthor() {
        return author;
    }


    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @Column(name = "created_data")
    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }


    public void setAuthor(long author) {
        this.author = author;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
