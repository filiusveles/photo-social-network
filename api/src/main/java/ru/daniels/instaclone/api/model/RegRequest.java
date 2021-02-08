package ru.daniels.instaclone.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class RegRequest {
    @Getter @Setter
    @JsonProperty("firstname")
    private String firstName;
    @Getter @Setter
    @JsonProperty("lastname")
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String phone;
    @Getter @Setter
    private String nickname;

}
