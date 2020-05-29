package com.example.ytmd.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Repositories.MusicRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Services.AsyncService;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchPlaylistViewModel extends ViewModel {

    public VideoRepository videoRepository;
    public AsyncService asyncService;
    public MusicRepository musicRepository;

    @Inject
    public SearchPlaylistViewModel(VideoRepository videoRepository, AsyncService asyncService, MusicRepository musicRepository) {
        this.videoRepository = videoRepository;
        this.asyncService = asyncService;
        this.musicRepository = musicRepository;

        searchPlaylistResultDataLive = videoRepository.getSearchPlayListResponseLiveData();
    }

    private final MutableLiveData<ArrayList<VideoSearchResult>> searchPlaylistResultDataLive;

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlaylistResultDataLive() {
        return searchPlaylistResultDataLive;
    }

    public void SearchPlaylist(String s) {
        videoRepository.SearchPlaylist(s);
    }

    public void DownloadPlaylist(VideoSearchResult item) {
        DownloadRequest request = new DownloadRequest(item.getId(),item.getTitle(),item.getImage());
        asyncService.DownloadPlaylist(request.getId(), request.getTitle());
    }
}
