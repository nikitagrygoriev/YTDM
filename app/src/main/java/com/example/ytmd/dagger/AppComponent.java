package com.example.ytmd.dagger;

import android.app.Application;
import android.content.Context;

import com.example.ytmd.Activities.MusicActivity;
import com.example.ytmd.Activities.SearchActivity;
import com.example.ytmd.Activities.SearchPlaylistActivity;
import com.example.ytmd.Activities.ShareActivity;
import com.example.ytmd.App;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App container (dependency injection)
 * Read more https://developer.android.com/training/dependency-injection/dagger-android
 */
@Component(modules = AppModule.class)
@Singleton
public abstract class AppComponent {

    public static AppComponent from(Context context){
        return ((App) context.getApplicationContext()).getAppComponent();
    }

    public abstract void inject(SearchActivity searchActivity);
    public abstract void inject(SearchPlaylistActivity searchPlaylistActivity);

    public abstract void inject(MusicActivity musicActivity);
    public abstract void inject(ShareActivity shareActivity);
}
