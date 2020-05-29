package com.example.ytmd.Models;

import android.graphics.Bitmap;

public class ShareDownloadRequest {
    String id;

    public ShareDownloadRequest(String id) {
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
