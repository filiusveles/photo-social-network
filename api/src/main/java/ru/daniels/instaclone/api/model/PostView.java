package ru.daniels.instaclone.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class PostView {
    @Getter
    private Long id;
    @Getter
    private String author;
    @Getter
    private String image;
    @Getter
    private String description;
    @Getter
    private List<String> tags;
    @JsonIgnore
    public static PostView.PostVewBuilder builder(){
        return new PostView.PostVewBuilder();
    }

    private PostView(String author, String image, String description, List<String> tags, Long id) {
        this.author = author;
        this.image = image;
        this.description = description;
        this.tags = tags;
        this.id = id;
    }

    public static class PostVewBuilder{
        private String author = "";
        private String image = "";
        private String description = "";
        private List<String> tags = new ArrayList<>();
        private Long id = -1L;


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

        public PostView build(){
            return new PostView(author, image, description, tags, id);
        }
    }

}
