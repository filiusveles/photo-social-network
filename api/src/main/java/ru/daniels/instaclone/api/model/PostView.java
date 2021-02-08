package ru.daniels.instaclone.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
public class PostView {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private List<String> tags;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Getter
    @Setter
    private Date createdDate;
    @Getter
    @Setter
    private String image;

    @JsonIgnore
    public static PostView.PostVewBuilder builder() {
        return new PostView.PostVewBuilder();
    }

    private PostView(String author, String image, String description,
                     List<String> tags, Long id, Date date, String title) {
        this.author = author;
        this.image = image;
        this.description = description;
        this.tags = tags;
        this.id = id;
        this.createdDate = date;
        this.title = title;
    }

    public static class PostVewBuilder {
        private String author = "";
        private String image = "";
        private String description = "";
        private List<String> tags = new ArrayList<>();
        private Long id = -1L;
        private Date createdDate = new Date();
        private String title;

        public PostView.PostVewBuilder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public PostView.PostVewBuilder setImage(String image) {
            this.image = image;
            return this;
        }

        public PostView.PostVewBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public PostView.PostVewBuilder setTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public PostView.PostVewBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public PostView.PostVewBuilder setCreatedDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public PostView.PostVewBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public PostView build() {
            return new PostView(author, image, description, tags, id, createdDate, title);
        }
    }

}
