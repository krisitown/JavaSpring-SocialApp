package com.social.models.viewModels;

public class UserViewModel {
    private long id;

    private String username;

    private boolean isFriendsWith;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsFriendsWith() {
        return isFriendsWith;
    }

    public void setIsFriendsWith(boolean friendsWith) {
        isFriendsWith = friendsWith;
    }
}
