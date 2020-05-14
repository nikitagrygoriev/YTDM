package com.example.ytmd.Services;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.ytmd.Models.DownloadPlaylistRequest;
import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Repositories.DownloadUrlRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.github.kotvertolet.youtubejextractor.exception.ExtractionException;
import com.github.kotvertolet.youtubejextractor.exception.YoutubeRequestException;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.inject.Inject;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Service responsible for async task request.
 * Read more (https://developer.android.com/reference/android/os/AsyncTask)
 */
public class NetworkService {

    private VideoRepository videoRepository;
    private DownloadUrlRepository downloadUrlRepository;
    private Context context;

    @Inject
    public NetworkService(VideoRepository videoRepository, DownloadUrlRepository downloadUrlRepository, Context context) {
        this.videoRepository = videoRepository;
        this.downloadUrlRepository = downloadUrlRepository;
        this.context = context;
    }

    public void SearchVideo(String keyword, OnDataloadListListener listener) {
        new SearchOperation(listener).execute(keyword);
    }

    public void SearchPlaylist(String keyword, OnDataloadListListener listener) {
        new SearchPlaylistOperation(listener).execute(keyword);
    }

    public void DownloadVideo(String id,String title) {
        new DownloadOperation().execute(new DownloadRequest(id, title));
    }

    public void DownloadPlaylist(String id,String title) {
        new DownloadPlaylist().execute(new DownloadPlaylistRequest(id, title));
    }



    private final class SearchOperation extends AsyncTask<String,Void,ArrayList<VideoSearchResult>> {

        private OnDataloadListListener onDataloadListListener;

        public SearchOperation(OnDataloadListListener onDataloadListListener ){

            this.onDataloadListListener = onDataloadListListener;
        }

        @Override
        protected  ArrayList<VideoSearchResult> doInBackground(String... params) {
            try {
                return  videoRepository.SearchVideos(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<VideoSearchResult> result) {
            if(onDataloadListListener != null){
                onDataloadListListener.onDataloadListReady(result);
            }
        }

        }

    private final class SearchPlaylistOperation extends AsyncTask<String,Void,ArrayList<VideoSearchResult>> {

        private OnDataloadListListener onDataloadListListener;

        public SearchPlaylistOperation(OnDataloadListListener onDataloadListListener ){

            this.onDataloadListListener = onDataloadListListener;
        }

        @Override
        protected  ArrayList<VideoSearchResult> doInBackground(String... params) {
            try {
                return  videoRepository.SearchPlaylist(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<VideoSearchResult> result) {
            if(onDataloadListListener != null){
                onDataloadListListener.onDataloadListReady(result);
            }
        }

    }

    /**
     * Downloading operation
     * TODO Saving downloaded music names to database
     * TODO Now we are saving music to download folder, maybe this should be changed ?
     */
    private final class DownloadOperation extends AsyncTask<DownloadRequest,Void, Void> {

        @Override
        protected  Void doInBackground(DownloadRequest... params) {

            try{
                Uri dwonloadUri = downloadUrlRepository.GetVideoDownloadUri(params[0].getId());

                DownloadManager.Request request = new DownloadManager.Request(dwonloadUri);

                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, params[0].getTitle() + ".mp3");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                request.allowScanningByMediaScanner();// if you want to be available from media players
                DownloadManager manager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(request);
            }catch (Exception e){

            }
            return null;
        }

    }

    private final class DownloadPlaylist extends AsyncTask<DownloadPlaylistRequest,Void, Void> {

        @Override
        protected  Void doInBackground(DownloadPlaylistRequest... params) {
            String playListName = params[0].getPlaylistName();
            String playlistId = params[0].getGetPlaylistId();

            try{
                ArrayList<PlaylistItem> playlistItems = videoRepository.GetPlayListVideos(playlistId);

                File folder = new File(Environment.DIRECTORY_DOWNLOADS +
                        File.separator + playListName);
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                for (PlaylistItem item:playlistItems) {
                    Uri dwonloadUri = downloadUrlRepository.GetVideoDownloadUri(item.getSnippet().getResourceId().getVideoId());

                    DownloadManager.Request request = new DownloadManager.Request(dwonloadUri);

                    request.setDestinationInExternalPublicDir(folder.getAbsolutePath(), item.getSnippet().getTitle());
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                    request.allowScanningByMediaScanner();// if you want to be available from media players
                    DownloadManager manager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                    manager.enqueue(request);
                }
            }catch (Exception e){

            }
            return null;
        }
    }
}
