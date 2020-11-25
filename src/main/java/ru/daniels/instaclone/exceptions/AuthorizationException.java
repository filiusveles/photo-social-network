package ru.daniels.instaclone.exceptions;

public class AuthorizationException extends IllegalArgumentException {
    public AuthorizationException(String s) {
        super(s);
    }
}
