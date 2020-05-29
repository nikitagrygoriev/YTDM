package com.example.ytmd.Services;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.ytmd.DAL.Entities.Music;
import com.example.ytmd.Helpers.BitmapHelper;
import com.example.ytmd.Models.DownloadPlaylistRequest;
import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.MyMusicResult;
import com.example.ytmd.Models.ShareDownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.Models.YoutubeVideoExtracted;
import com.example.ytmd.Repositories.DownloadUrlRepository;
import com.example.ytmd.Repositories.MusicRepository;
import com.example.ytmd.Repositories.VideoRepository;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Service responsible for async task request.
 * Read more (https://developer.android.com/reference/android/os/AsyncTask)
 */
public class AsyncService {

    private VideoRepository videoRepository;
    private DownloadUrlRepository downloadUrlRepository;
    private Context context;
    private MusicRepository musicRepository;

    @Inject
    public AsyncService(VideoRepository videoRepository, DownloadUrlRepository downloadUrlRepository, Context context, MusicRepository musicRepository) {
        this.videoRepository = videoRepository;
        this.downloadUrlRepository = downloadUrlRepository;
        this.context = context;
        this.musicRepository = musicRepository;
    }



    public void DownloadVideo(DownloadRequest request) {
        new DownloadOperation().execute(request);
    }

    public void DownloadVideo(ShareDownloadRequest request) {
        new ShareDownloadOperation().execute(request);
    }

    public void DownloadPlaylist(String id,String title) {
        new DownloadPlaylist().execute(new DownloadPlaylistRequest(id, title));
    }

    public void GetMyMusic(OnMusicDownloadedListener listener) {
        new PopulateMyMusic(listener).execute();
    }

    /**
     * Downloading operation
     * TODO Saving downloaded music names to database
     * TODO Now we are saving music to download folder, maybe this should be changed ?
     */
    private final class DownloadOperation extends AsyncTask<DownloadRequest,DownloadRequest, DownloadRequest> {

        @Override
        protected  DownloadRequest doInBackground(DownloadRequest... params) {

            try{
                DownloadRequest downloadRequest = params[0];

                Bitmap bitmap = downloadRequest.getImage();
                byte[] byteArray = BitmapHelper.BitmapToBytes(bitmap);

                Music music = new Music(downloadRequest.getTitle(),byteArray, new Date());
                musicRepository.InsertMusic(music);



                List<Music> musics = musicRepository.GetAllMusic();


                Uri dwonloadUri = downloadUrlRepository.GetVideoDownloadUri(downloadRequest.getId());

                DownloadManager.Request request = new DownloadManager.Request(dwonloadUri);

                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, params[0].getTitle() + ".mp3");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                request.allowScanningByMediaScanner();// if you want to be available from media players
                DownloadManager manager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(request);

                return  params[0];
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

    private final class PopulateMyMusic extends AsyncTask<Void,Void, List<Music>> {

        private OnMusicDownloadedListener onMusicDownloadedListener;

        public PopulateMyMusic(OnMusicDownloadedListener onMusicDownloadedListener ){

            this.onMusicDownloadedListener = onMusicDownloadedListener;
        }

        @Override
        protected  List<Music> doInBackground(Void... voids) {

            return musicRepository.GetAllMusic();
        }

        @Override
        protected void onPostExecute(List<Music> myMusics) {
            if(onMusicDownloadedListener != null){
                ArrayList<MyMusicResult> myMusicResults = new ArrayList<>();

                for (Music music: myMusics) {
                    Bitmap image = BitmapHelper.BytesToBitmap(music.getImage());
                    MyMusicResult myMusicResult = new MyMusicResult(music.getTitle(),music.getDownloadDate(),image);

                    myMusicResults.add(myMusicResult);
                }

                onMusicDownloadedListener.OnMusicDownloadedReady(myMusicResults);
            }
        }
    }

    private final class ShareDownloadOperation extends AsyncTask<ShareDownloadRequest,Void, Void> {

        @Override
        protected  Void doInBackground(ShareDownloadRequest... params) {
            try{
                ShareDownloadRequest downloadRequest = params[0];
                String id = downloadRequest.getId();

                YoutubeVideoExtracted videoExtracted = downloadUrlRepository.GetVideoDdetails(id);

                DownloadManager.Request request = new DownloadManager.Request(videoExtracted.getDownloadUri());

                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, videoExtracted.getDetails().getTitle() + ".mp3");
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); // to notify when download is complete
                request.allowScanningByMediaScanner();// if you want to be available from media players
                DownloadManager manager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                manager.enqueue(request);

                return  null;
            }catch (Exception e){

            }
            return null;
        }
    }

}
