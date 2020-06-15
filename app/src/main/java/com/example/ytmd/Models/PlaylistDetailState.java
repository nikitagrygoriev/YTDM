package com.example.ytmd.Models;

import java.util.List;

public class PlaylistDetailState {
    private String title;

    public PlaylistDetailState(String title, List<VideoSearchResult> videos) {
        this.title = title;
        this.videos = videos;
    }

    private List<VideoSearchResult> videos;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<VideoSearchResult> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoSearchResult> videos) {
        this.videos = videos;
    }
}
