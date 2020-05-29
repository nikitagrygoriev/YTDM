package com.example.ytmd.dagger;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.ytmd.DAL.AppDatabase;
import com.example.ytmd.DAL.MusicDao;
import com.example.ytmd.ViewModels.SearchViewModel;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.squareup.picasso.Picasso;

import javax.inject.Provider;
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

    @Provides
    @Singleton
    public MusicDao provideMusicDao(){
        AppDatabase db = Room.databaseBuilder(appContext,
                AppDatabase.class, "database-name").build();

        return db.musicDao();
    }

    @Provides
    @Singleton
    public Picasso providePicasso(){

        return Picasso.get();
    }
}
