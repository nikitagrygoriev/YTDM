package com.example.ytmd.Models;

import android.graphics.Bitmap;

import java.util.Date;

public class MyMusicResult {
    private String title;
    private Date DownloadedDate;
    private Bitmap image;

    public MyMusicResult(String title, Date downloadedDate, Bitmap image) {
        this.title = title;
        DownloadedDate = downloadedDate;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDownloadedDate() {
        return DownloadedDate;
    }

    public void setDownloadedDate(Date downloadedDate) {
        DownloadedDate = downloadedDate;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
