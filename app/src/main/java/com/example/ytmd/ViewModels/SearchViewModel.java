package com.example.ytmd.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Repositories.MusicRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Services.AsyncService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    public VideoRepository videoRepository;
    public AsyncService asyncService;
    public MusicRepository musicRepository;

    @Inject
    public SearchViewModel(VideoRepository videoRepository, AsyncService asyncService, MusicRepository musicRepository) {
        this.videoRepository = videoRepository;
        this.asyncService = asyncService;
        this.musicRepository = musicRepository;

        searchResultDataLive = videoRepository.getSearchListResponseLiveData();
    }

    private final MutableLiveData<ArrayList<VideoSearchResult>> searchResultDataLive;

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchResultDataLive() {
        return searchResultDataLive;
    }

    public void SearchVideo(String s) {
        videoRepository.SearchVideos(s);
    }

    public void DownloadVideo(VideoSearchResult item) {
        DownloadRequest request = new DownloadRequest(item.getId(),item.getTitle(),item.getImage());
        asyncService.DownloadVideo(request);
    }

}
