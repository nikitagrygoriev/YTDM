package com.example.ytmd.Repositories;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.ytmd.Models.VideoSearchResult;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class VideoRepository {

    private YouTubeApiRepository youTubeApiRepository;
    private Context context;
    private Picasso picasso;

    @Inject
    public VideoRepository(YouTubeApiRepository youTubeApiRepository , Context context, Picasso picasso) {
        this.youTubeApiRepository = youTubeApiRepository;
        this.context = context;
    }

    /*
        Method returns YouTube videos by search keywordd (default 15 results)
     */
    public ArrayList<VideoSearchResult> SearchVideos(String keyword) throws IOException {
        SearchListResponse searchResponse = youTubeApiRepository.SearchVideosRequest(keyword);

        List<SearchResult> results = searchResponse.getItems();
        ArrayList<VideoSearchResult> videoList = new ArrayList<>();


        picasso = Picasso.get();
        // Maping results to VideoSearchResult
        for (SearchResult result: results) {
            SearchResultSnippet snipper = result.getSnippet();
            Bitmap image = picasso.load(snipper.getThumbnails().getMedium().getUrl())
                    .get();

            VideoSearchResult video = new VideoSearchResult
                    (snipper.getTitle(),snipper.getDescription(),result.getId().getVideoId(),image);

            videoList.add(video);
        }

        return  videoList;
    }

    /*
        Method returns YouTube plalist by search keywordd (default 15 results)
     */
    public ArrayList<VideoSearchResult> SearchPlaylist(String keyword) throws IOException {
        SearchListResponse searchResponse = youTubeApiRepository.SearchPlaylistsRequest(keyword);

        List<SearchResult> results = searchResponse.getItems();
        ArrayList<VideoSearchResult> videoList = new ArrayList<>();

        // Maping results to VideoSearchResult TODO Make PlaylistSearchResult
        for (SearchResult result: results) {
            SearchResultSnippet snipper = result.getSnippet();
            Bitmap image = picasso.load(snipper.getThumbnails().getMedium().getUrl()).get();

            VideoSearchResult video = new VideoSearchResult
                    (snipper.getTitle(),snipper.getDescription(),result.getId().getPlaylistId(),image);

            videoList.add(video);
        }

        return  videoList;
    }

    /*
        Method returns YouTube Videos by playlist id (default 20 results)
     */
    public ArrayList<PlaylistItem> GetPlayListVideos(String playlistId) throws IOException {
        PlaylistItemListResponse searchResponse = youTubeApiRepository.SeardhPlaylistsVideoRequest(playlistId);

        List<PlaylistItem> results = searchResponse.getItems();
        return (ArrayList<PlaylistItem>) results;
    }
}

