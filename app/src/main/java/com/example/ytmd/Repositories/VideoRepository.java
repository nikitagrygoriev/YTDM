package com.example.ytmd.Repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import androidx.lifecycle.MutableLiveData;

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

/**
 * https://stackoverflow.com/questions/51601046/should-i-make-asynctask-member-of-livedata-or-repository-class-as-replacement/51631757
 */
public class VideoRepository {

    private YouTubeApiRepository youTubeApiRepository;
    private Context context;
    private Picasso picasso;

    @Inject
    public VideoRepository(YouTubeApiRepository youTubeApiRepository , Context context, Picasso picasso) {
        this.youTubeApiRepository = youTubeApiRepository;
        this.context = context;
    }

    private MutableLiveData<ArrayList<VideoSearchResult>> searchListResponseLiveData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<VideoSearchResult>> searchPlayListResponseLiveData = new MutableLiveData<>();

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchListResponseLiveData() {
        return searchListResponseLiveData;
    }

    public MutableLiveData<ArrayList<VideoSearchResult>> getSearchPlayListResponseLiveData() {
        return searchPlayListResponseLiveData;
    }

    /**
     * Method returns YouTube videos by search keywordd (default 15 results)
     */
    public void SearchVideos(String keyword) {
        new AsyncTask<String, Void, ArrayList<VideoSearchResult>>() {
            @Override
            protected ArrayList<VideoSearchResult> doInBackground(String... strings) {
                SearchListResponse searchResponse = null;
                try {
                    searchResponse = youTubeApiRepository.SearchVideosRequest(keyword);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                List<SearchResult> results = searchResponse.getItems();
                ArrayList<VideoSearchResult> videoList = new ArrayList<>();

                picasso = Picasso.get();
                // Maping results to VideoSearchResult
                for (SearchResult result: results) {
                    SearchResultSnippet snipper = result.getSnippet();
                    Bitmap image = null;
                    try {
                        image = picasso.load(snipper.getThumbnails().getMedium().getUrl())
                                .get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    VideoSearchResult video = new VideoSearchResult
                            (snipper.getTitle(),snipper.getDescription(),result.getId().getVideoId(),image);

                    videoList.add(video);
                }
                return videoList;
            }

            @Override
            protected void onPostExecute(ArrayList<VideoSearchResult> searchResults) {
                searchListResponseLiveData.setValue(searchResults);
            }
        }.execute();
    }

    /**
     * Method returns YouTube plalist by search keywordd (default 15 results)
     */
    public void SearchPlaylist(String keyword) {
        new AsyncTask<String, Void, ArrayList<VideoSearchResult>>() {
            @Override
            protected ArrayList<VideoSearchResult> doInBackground(String... strings) {
                SearchListResponse searchResponse = null;
                try {
                    searchResponse = youTubeApiRepository.SearchPlaylistsRequest(keyword);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                List<SearchResult> results = searchResponse.getItems();
                ArrayList<VideoSearchResult> videoList = new ArrayList<>();

                picasso = Picasso.get();

                for (SearchResult result: results) {
                    SearchResultSnippet snipper = result.getSnippet();
                    Bitmap image = null;
                    try {
                        image = picasso.load(snipper.getThumbnails().getMedium().getUrl()).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    VideoSearchResult video = new VideoSearchResult
                            (snipper.getTitle(),snipper.getDescription(),result.getId().getPlaylistId(),image);

                    videoList.add(video);
                }

                return  videoList;
            }

            @Override
            protected void onPostExecute(ArrayList<VideoSearchResult> searchResults) {
                searchPlayListResponseLiveData.setValue(searchResults);
            }
        }.execute();


    }

    /**
     * Method returns YouTube Videos by playlist id (default 20 results)
     */
    public ArrayList<PlaylistItem> GetPlayListVideos(String playlistId) throws IOException {
        PlaylistItemListResponse searchResponse = youTubeApiRepository.SeardhPlaylistsVideoRequest(playlistId);

        List<PlaylistItem> results = searchResponse.getItems();
        return (ArrayList<PlaylistItem>) results;
    }
}

