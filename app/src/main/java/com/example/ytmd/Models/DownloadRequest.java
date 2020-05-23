package com.example.ytmd.Models;

import android.graphics.Bitmap;

public class DownloadRequest {
    String id;
    String title;
    Bitmap image;

    public DownloadRequest(String id, String title, Bitmap image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public DownloadRequest(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
