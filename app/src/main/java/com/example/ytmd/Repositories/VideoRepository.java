package com.example.ytmd.Repositories;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.ytmd.Models.VideoSearchResult;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class VideoRepository {

    private YouTube client;

    @Inject
    public VideoRepository(YouTube client) {
        this.client = client;
    }

    public ArrayList<VideoSearchResult> SearchVideos(String keyword) throws IOException {

        YouTube.Search.List request = client
                .search()
                .list("snippet")
                .setQ(keyword)
                .setMaxResults(30L);

        SearchListResponse searchResponse = request.execute();

        List<SearchResult> results = searchResponse.getItems();
        ArrayList<VideoSearchResult> videoList = new ArrayList<>();

        for (SearchResult result: results) {
            SearchResultSnippet snipper = result.getSnippet();
            Bitmap image = getImageBitmap(snipper.getThumbnails().getMedium().getUrl());

            VideoSearchResult video = new VideoSearchResult
                    (snipper.getTitle(),snipper.getDescription(),result.getId().getVideoId(),image);

            videoList.add(video);
        }

        return  videoList;
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            //Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

}
