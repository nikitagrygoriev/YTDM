package com.example.ytmd.dagger;

import android.content.Context;

import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Repositories.YouTubeApiRepository;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context appContext;

    public AppModule(Context appContext) {
        this.appContext = appContext;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return appContext;
    }

    @Provides
    public YouTube provideYouTube(){
        return  new YouTube.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(),null)
                . setApplicationName("om.example.ytmd")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer("AIzaSyCe7suU3MCpwSGoOrHfcBuCMj4U0pmV_Ns"))
                .build();
    }

   // public VideoRepository provideVideoRepository(YouTube client){
  //      return new VideoRepository(new YouTubeApiRepository(client), appContext);
   // }
}
