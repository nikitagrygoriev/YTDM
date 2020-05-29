package com.example.ytmd.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ytmd.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchActivityBtn = findViewById(R.id.btnSearch);
        searchActivityBtn.setOnClickListener(v -> {
            Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
            startActivity(searchIntent);
        });

        Button searchPlaylistActivityBtn = findViewById(R.id.btnSearchPlaylist);
        searchPlaylistActivityBtn.setOnClickListener(v -> {
            Intent searchPlaylistIntent = new Intent(getApplicationContext(), SearchPlaylistActivity.class);
            startActivity(searchPlaylistIntent);
        });
        
        Button myMusicActivityBtn = findViewById(R.id.btnMusic);
        myMusicActivityBtn.setOnClickListener(v -> {
            Intent searchIntent = new Intent(getApplicationContext(), MusicActivity.class);
            startActivity(searchIntent);
        });

    }
}
