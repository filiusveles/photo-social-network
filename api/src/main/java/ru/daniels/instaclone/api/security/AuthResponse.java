package ru.daniels.instaclone.api.security;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.daniels.instaclone.api.model.Profile;

@NoArgsConstructor
public class AuthResponse {
    @Getter @Setter
    private Profile profile;
    @Getter @Setter
    private String jwtToken;

    public AuthResponse(String jwtToken, Profile profile) {
        this.profile = profile;
        this.jwtToken = jwtToken;
    }
}
