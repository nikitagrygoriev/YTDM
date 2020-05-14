package com.example.ytmd;

import android.app.Application;

import com.example.ytmd.dagger.AppComponent;
import com.example.ytmd.dagger.AppModule;
import com.example.ytmd.dagger.DaggerAppComponent; // Sciezka powinna sie pojawic po pomyslnym buildzie

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
