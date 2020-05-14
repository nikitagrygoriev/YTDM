package com.example.ytmd.Repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.ytmd.Models.VideoSearchResult;
import com.github.kotvertolet.youtubejextractor.YoutubeJExtractor;
import com.github.kotvertolet.youtubejextractor.exception.ExtractionException;
import com.github.kotvertolet.youtubejextractor.exception.YoutubeRequestException;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.StreamingData;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.VideoDetails;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.YoutubeVideoData;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class VideoRepository {

    private YouTubeApiRepository youTubeApiRepository;
    private Context context;

    @Inject
    public VideoRepository(YouTubeApiRepository youTubeApiRepository , Context context) {
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

        // Maping results to VideoSearchResult
        for (SearchResult result: results) {
            SearchResultSnippet snipper = result.getSnippet();
            Bitmap image = Picasso.get()
                    .load(snipper.getThumbnails().getMedium().getUrl())
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
            Bitmap image = Picasso.get().load(snipper.getThumbnails().getMedium().getUrl()).get();

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

