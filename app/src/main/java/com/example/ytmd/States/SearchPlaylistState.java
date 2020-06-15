package com.example.ytmd.States;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ytmd.Models.VideoSearchResult;

import java.util.ArrayList;

public class SearchPlaylistState {
    private final MutableLiveData<ArrayList<VideoSearchResult>> searchPlaylistResultDataLive;
    private final MutableLiveData<ArrayList<VideoSearchResult>> searchPlaylistVideoResultDataLive;
    private final MutableLiveData<String> playlistTitle;
    private ArrayList<VideoSearchResult> checkedVideosToDownload;

    public SearchPlaylistState() {
        searchPlaylistResultDataLive = new MutableLiveData<>();
        playlistTitle = new MutableLiveData<>("");
        searchPlaylistVideoResultDataLive = new MediatorLiveData<>();
        checkedVideosToDownload = new ArrayList<>();
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlaylistResultDataLive() {
        return searchPlaylistResultDataLive;
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlaylistVideoResultDataLive() {
        return searchPlaylistVideoResultDataLive;
    }

    public MutableLiveData<String> getPlaylistTitle() {
        return playlistTitle;
    }

    public ArrayList<VideoSearchResult> getCheckedVideosToDownload() {
        return checkedVideosToDownload;
    }
}
