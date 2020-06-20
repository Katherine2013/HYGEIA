package com.example.hygeia.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;

    private String password;
    private Integer credit;

    // TODO: credit初始为10
    public LoggedInUser(String userId, String displayName, String password, Integer credit) {
        this.userId = userId;
        this.displayName = displayName;
        this.password = password;
        this.credit = credit;
    }

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getCredit() {
        return credit;
    }
}