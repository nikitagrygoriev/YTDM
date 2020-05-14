package com.example.ytmd.Repositories;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.IOException;

import javax.inject.Inject;

/**
 * YouTube api consumer repository
 */
public class YouTubeApiRepository {

    private YouTube ytClient;
    private long defaultMaxNumber;

    @Inject
    public YouTubeApiRepository(YouTube ytClient) {
        this.ytClient = ytClient;
        defaultMaxNumber = 15L;
    }

    /**
      * https://developers.google.com/youtube/v3/docs/search/list
      */
    public SearchListResponse SearchVideosRequest(String keyword) throws IOException {
        YouTube.Search.List request = ytClient
                .search()
                 //The part parameter specifies a comma-separated list of one or more search
                 //resource properties that the API response will include.
                .list("snippet")
                 //The q parameter specifies the query term to search for.
                .setQ(keyword)
                 // Setting type we want (Video)
                .setType("video")
                .setMaxResults(defaultMaxNumber);

        SearchListResponse searchResponse = request.execute();

        return searchResponse;
    }

    /**
      * https://developers.google.com/youtube/v3/docs/search/list
      */
    public SearchListResponse SearchPlaylistsRequest(String keyword) throws IOException {
        YouTube.Search.List request = ytClient
                .search()
                .list("snippet")
                .setQ(keyword)
                .setType("playlist")
                .setMaxResults(defaultMaxNumber);

        SearchListResponse searchResponse = request.execute();

        return searchResponse;
    }

    /**
      * https://developers.google.com/youtube/v3/docs/playlists/list
      */
    public PlaylistItemListResponse SeardhPlaylistsVideoRequest(String playlistId) throws IOException {
        YouTube.PlaylistItems.List request = ytClient
                .playlistItems()
                .list("snippet")
                .setPlaylistId(playlistId)
                .setMaxResults(20L);

        PlaylistItemListResponse searchResponse = request.execute();
        return searchResponse;
    }
}
