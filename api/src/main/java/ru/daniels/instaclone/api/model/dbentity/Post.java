package ru.daniels.instaclone.api.model.dbentity;

import org.springframework.format.annotation.DateTimeFormat;
import ru.daniels.instaclone.api.security.SecUser;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="posts", schema = "data")
public class Post implements DBEntity {

    private long id;
    private String title;
    private String description;
    private SecUser author;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date date;
    private Image image;
    private List<Tag> tags;


    @Id
    @SequenceGenerator(name="posts_seq", sequenceName = "data.posts_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @Column(name = "id")
    public long getId() {
        return id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public SecUser getAuthor() {
        return author;
    }

    @OneToOne
    @JoinColumn(name = "image_id")
    public Image getResultImage() {
        return image;
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

    public void setId(long id) {
        this.id = id;
    }

    public void setAuthor(SecUser author) {
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

    public void setResultImage(Image image) {
        this.image = image;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", resultImage=" + image +
                '}';
    }
}
