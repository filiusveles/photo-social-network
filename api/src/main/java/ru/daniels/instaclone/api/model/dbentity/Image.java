package ru.daniels.instaclone.api.model.dbentity;


import javax.persistence.*;

@Entity
@Table(name="images", schema = "data")
public class Image implements DBEntity {
    private long id;
    private String imageURL;

    @Id
    @SequenceGenerator(name="images_seq", sequenceName = "data.images_id_seq", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "images_seq")
    @Column(name="id")
    public long getId() {
        return id;
    }

    @Column(name="image_filename")
    public String getImageURL() {
        return imageURL;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setImageURL(String image) {
        this.imageURL = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                '}';
    }
}
