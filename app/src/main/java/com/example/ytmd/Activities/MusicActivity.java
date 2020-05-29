package com.example.ytmd.Activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Adapters.ListAdapterMyMusic;
import com.example.ytmd.Models.MyMusicResult;
import com.example.ytmd.R;
import com.example.ytmd.Services.AsyncService;
import com.example.ytmd.Services.OnMusicDownloadedListener;
import com.example.ytmd.dagger.AppComponent;

import java.util.List;

import javax.inject.Inject;

public class MusicActivity extends AppCompatActivity {
    @Inject
    public AsyncService asyncService;

    RecyclerView musicList;
    String[] titles, channel;
    int images[] = new int[6];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent.from(this).inject(this);

        setContentView(R.layout.activity_music);
        Context context = this;

        asyncService.GetMyMusic(result -> {

            musicList = findViewById(R.id.musicList);
            ListAdapterMyMusic adapter = new ListAdapterMyMusic(context, result, R.layout.my_music_view_detail);

            musicList.setAdapter(adapter);
            musicList.setLayoutManager(new LinearLayoutManager(context));
        });
    }
}
