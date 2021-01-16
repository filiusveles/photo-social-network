package ru.daniels.instaclone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class Profile {
    @JsonProperty
    private final long id;
    @JsonProperty
    private final String firstName;
    @JsonProperty
    private final String lastName;
    @JsonProperty
    private final String email;
    @JsonProperty
    private final String phone;
    @JsonProperty
    private final String nickname;
    @JsonProperty
    private final String avatar;


    public static Profile.ProfileBuilder builder(){
        return new Profile.ProfileBuilder();
    }

    private Profile(long id, String firstName, String lastName, String email,  String phone,
                   String nickname, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.nickname = nickname;
        this.avatar = avatar;
    }


    @ToString(of={"id", "nickname"})
    public static class ProfileBuilder{
        private long id;
        private String firstName = "";
        private String lastName = "";
        private String email = "";
        private String phone = "";
        private String nickname = "";
        private String avatar = "";

        ProfileBuilder() {}

        public Profile.ProfileBuilder setId(long id) {
            this.id = id;
            return this;
        }

        public Profile.ProfileBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Profile.ProfileBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Profile.ProfileBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Profile.ProfileBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Profile.ProfileBuilder setNickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Profile.ProfileBuilder setAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Profile build(){
            return new Profile(id, firstName, lastName, email, phone, nickname, avatar);
        }
    }
}
