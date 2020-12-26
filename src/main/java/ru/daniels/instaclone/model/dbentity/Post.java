package ru.daniels.instaclone.model.dbentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="posts", schema = "data")
public class Post implements DBEntity {

    private long id;
    @JsonProperty("image")
    private String base64image;
    @JsonProperty("post_title")
    private String title;
    @JsonProperty("description")
    private String description;

    private User author;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private Image resultImage;
    private List<Tag> tags;


    @Id
    @SequenceGenerator(name="posts_seq", sequenceName = "data.posts_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public User getAuthor() {
        return author;
    }

    @OneToOne
    @JoinColumn(name = "image_id")
    public Image getResultImage() {
        return resultImage;
    }

    @Column(name = "created_data")
    public Date getDate() {
        return date;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @JoinTable(name="tagmap",
            schema = "data",
            joinColumns = @JoinColumn(
                    name = "post_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "tag_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    public List<Tag> getTags() {
        return tags;
    }

    @Transient
    public String getBase64image() {
        return base64image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setResultImage(Image resultImage) {
        this.resultImage = resultImage;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setBase64image(String base64image) {
        this.base64image = base64image;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", resultImage=" + resultImage +
                '}';
    }
}