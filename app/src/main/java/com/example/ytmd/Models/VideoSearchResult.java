package com.example.ytmd.Models;

import android.graphics.Bitmap;

public class VideoSearchResult {
    private String title;
    private String description;
    private String id;
    private Bitmap image;

    public VideoSearchResult(String title, String description, String id, Bitmap image) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
