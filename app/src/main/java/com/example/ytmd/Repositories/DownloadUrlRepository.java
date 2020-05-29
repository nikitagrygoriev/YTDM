package com.example.ytmd.Repositories;

import android.net.Uri;

import com.example.ytmd.Models.YoutubeVideoExtracted;
import com.github.kotvertolet.youtubejextractor.YoutubeJExtractor;
import com.github.kotvertolet.youtubejextractor.exception.ExtractionException;
import com.github.kotvertolet.youtubejextractor.exception.YoutubeRequestException;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.StreamingData;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.VideoDetails;
import com.github.kotvertolet.youtubejextractor.models.youtube.videoData.YoutubeVideoData;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.inject.Inject;

public class DownloadUrlRepository {

    @Inject
    public DownloadUrlRepository() {
    }

    public Uri GetVideoDownloadUri(String id) throws YoutubeRequestException, ExtractionException, MalformedURLException, URISyntaxException {

        YoutubeJExtractor youtubeJExtractor = new YoutubeJExtractor();
        YoutubeVideoData videoData;

        videoData = youtubeJExtractor.extract(id);

        VideoDetails details = videoData.getVideoDetails();
        String Title = details.getTitle();

        StreamingData streamingData = videoData.getStreamingData();
        URL uri = new URL( streamingData.getAdaptiveAudioStreams().get(0).getUrl());

        return android.net.Uri.parse(uri.toURI().toString());
    }

    public YoutubeVideoExtracted GetVideoDdetails(String id) throws YoutubeRequestException, ExtractionException, MalformedURLException, URISyntaxException {
        YoutubeJExtractor youtubeJExtractor = new YoutubeJExtractor();
        YoutubeVideoData videoData;

        videoData = youtubeJExtractor.extract(id);



        VideoDetails details = videoData.getVideoDetails();
        String Title = details.getTitle();

        StreamingData streamingData = videoData.getStreamingData();
        URL url = new URL( streamingData.getAdaptiveAudioStreams().get(0).getUrl());
        Uri uri = android.net.Uri.parse(url.toURI().toString());
        YoutubeVideoExtracted videoExtracted = new YoutubeVideoExtracted(details, uri);

        return videoExtracted;
    }

}
