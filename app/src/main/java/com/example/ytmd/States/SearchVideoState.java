package com.example.ytmd.States;

import androidx.lifecycle.MutableLiveData;

import com.example.ytmd.Models.VideoSearchResult;

import java.util.ArrayList;

public class SearchVideoState {

    private final MutableLiveData<ArrayList<VideoSearchResult>> searchPlaylistResultDataLive;

    public SearchVideoState() {
        searchPlaylistResultDataLive = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchVideoResultDataLive() {
        return searchPlaylistResultDataLive;
    }
}
