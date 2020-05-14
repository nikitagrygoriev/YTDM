package com.example.ytmd.Models;

import java.util.ArrayList;

public class DownloadPlaylistRequest {
    String getPlaylistId;
    String playlistName;

    public DownloadPlaylistRequest(String getPlaylistId, String playlistName) {
        this.getPlaylistId = getPlaylistId;
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getGetPlaylistId() {
        return getPlaylistId;
    }

    public void setGetPlaylistId(String getPlaylistId) {
        this.getPlaylistId = getPlaylistId;
    }
}
