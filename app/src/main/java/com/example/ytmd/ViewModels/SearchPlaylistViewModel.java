package com.example.ytmd.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.PlaylistDetailState;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Repositories.MusicRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Services.AsyncService;
import com.example.ytmd.States.SearchPlaylistState;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;

public class SearchPlaylistViewModel extends ViewModel {

    // CORE
    public VideoRepository videoRepository;
    public AsyncService asyncService;
    public MusicRepository musicRepository;

    // STATE
    private SearchPlaylistState state;

    @Inject
    public SearchPlaylistViewModel(VideoRepository videoRepository, AsyncService asyncService, MusicRepository musicRepository) {
        this.videoRepository = videoRepository;
        this.asyncService = asyncService;
        this.musicRepository = musicRepository;

        state = new SearchPlaylistState();
    }

    // LIVE DATA
    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlaylistResultDataLive() {
        return state.getSearchPlaylistResultDataLive();
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlaylistVideoResultDataLive() {
        return state.getSearchPlaylistVideoResultDataLive();
    }

    public MutableLiveData<String> getPlaylistTitle() {
        return state.getPlaylistTitle();
    }

    // INTERFACE
    public void SearchPlaylist(String keyWord) {
        videoRepository.SearchPlaylist(keyWord, result -> state.getSearchPlaylistResultDataLive().setValue(result));
    }

    public void DownloadPlaylist(VideoSearchResult item) {
        DownloadRequest request = new DownloadRequest(item.getId(),item.getTitle(),item.getImage());
        asyncService.DownloadPlaylist(request.getId(), request.getTitle());
    }

    public void DownloadCheckedVideos() {
        asyncService.DownloadPlaylist(state.getPlaylistTitle().getValue(), state.getCheckedVideosToDownload());
    }

    public void SetPlaylistDetail(VideoSearchResult item) {
        state.getCheckedVideosToDownload().clear();
        state.getPlaylistTitle().setValue(item.getTitle());
        videoRepository.SearchPlaylistVidedos(item.getId(), result -> state.getSearchPlaylistVideoResultDataLive().setValue(result));
    }

    public void UncheckVideoToDownload(VideoSearchResult item) {
        if(state.getCheckedVideosToDownload().contains(item)){
            state.getCheckedVideosToDownload().remove(item);
        }
    }

    public void CheckVideoToDownload(VideoSearchResult item) {
        if(!state.getCheckedVideosToDownload().contains(item)){
            state.getCheckedVideosToDownload().add(item);
        }
    }
}
