package com.github.model;

public class AuthorModel {

    private int authorId;

    private String author;

    private String avatar;

    private String banner;

    public AuthorModel(int authorId, String author, String avatar, String banner) {
        this.authorId = authorId;
        this.author = author;
        this.avatar = avatar;
        this.banner = banner;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }
}
