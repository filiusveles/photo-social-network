package ru.daniels.instaclone.security;

public enum Role {
    GUEST("guest"),
    USER("user");
    private String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
