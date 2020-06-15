package com.example.ytmd.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Repositories.MusicRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Services.AsyncService;
import com.example.ytmd.States.SearchVideoState;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

    // CORE
    public VideoRepository videoRepository;
    public AsyncService asyncService;
    public MusicRepository musicRepository;

    // STATE
    private SearchVideoState state;

    @Inject
    public SearchViewModel(VideoRepository videoRepository, AsyncService asyncService, MusicRepository musicRepository) {
        this.videoRepository = videoRepository;
        this.asyncService = asyncService;
        this.musicRepository = musicRepository;

        state = new SearchVideoState();
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchResultDataLive() {
        return state.getSearchVideoResultDataLive();
    }

    public void SearchVideo(String keyWord) {
        videoRepository.SearchVideos(keyWord, result -> state.getSearchVideoResultDataLive().setValue(result));
    }

    public void DownloadVideo(VideoSearchResult item) {
        DownloadRequest request = new DownloadRequest(item.getId(),item.getTitle(),item.getImage());
        asyncService.DownloadVideo(request);
    }
}
