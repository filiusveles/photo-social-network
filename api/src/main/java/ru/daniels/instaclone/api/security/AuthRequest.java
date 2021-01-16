package ru.daniels.instaclone.api.security;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class AuthRequest {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;
}
