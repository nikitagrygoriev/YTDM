package com.example.ytmd;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class MusicActivity extends AppCompatActivity {
    RecyclerView musicList;
    String[] titles, channel;
    int images[] = new int[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Resources res = getResources();
        Arrays.fill(images, R.drawable.album);
        musicList = findViewById(R.id.musicList);
        titles = res.getStringArray(R.array.titles);
        channel = res.getStringArray(R.array.channel);
        ListAdapterSearch adapter = new ListAdapterSearch(this, titles, channel, images, R.layout.my_music_view_detail);
        musicList.setAdapter(adapter);
        musicList.setLayoutManager(new LinearLayoutManager(this));
    }
}
