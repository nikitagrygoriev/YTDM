package com.example.ytmd.Models;

import android.net.Uri;

import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.VideoDetails;

import java.net.URL;

public class YoutubeVideoExtracted {
    private VideoDetails details;
    private Uri downloadUri;

    public YoutubeVideoExtracted(VideoDetails details, Uri downloadUri) {
        this.details = details;
        this.downloadUri = downloadUri;
    }

    public VideoDetails getDetails() {
        return details;
    }

    public void setDetails(VideoDetails details) {
        this.details = details;
    }

    public Uri getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(Uri downloadUri) {
        this.downloadUri = downloadUri;
    }
}
