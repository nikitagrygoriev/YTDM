package com.example.ytmd.Models;

import java.util.ArrayList;

public class DownloadCheckedVideos {
    String playlistName;
    ArrayList<VideoSearchResult> videos;

    public DownloadCheckedVideos(String playlistName, ArrayList<VideoSearchResult> videos) {
        this.playlistName = playlistName;
        this.videos = videos;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public ArrayList<VideoSearchResult> getVideos() {
        return videos;
    }
}
