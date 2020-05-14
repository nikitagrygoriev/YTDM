package com.example.ytmd.Activities;

import android.Manifest;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Adapters.ListAdapterSearch;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.Services.NetworkService;
import com.example.ytmd.Services.OnDataloadListListener;
import com.example.ytmd.dagger.AppComponent;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;


public class SearchActivity extends AppCompatActivity {
    @Inject
    public  VideoRepository videoRepository;

    @Inject
    public NetworkService networkService;

    RecyclerView searchList;
    ProgressBar pgsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        AppComponent.from(this).inject(this);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);

        Button searchActivityBtn = (Button) findViewById(R.id.button2);

        searchActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String search = editText.getText().toString();

                pgsBar.setVisibility(v.VISIBLE);

                networkService.SearchVideo(search, new OnDataloadListListener() {
                    @Override
                    // Method is executed after the youTube api request
                    // read more https://stackoverflow.com/questions/36389375/how-to-get-list-items-from-the-asynctask-to-main-activity
                    public void onDataloadListReady(ArrayList<VideoSearchResult> searchResults) {
                        PopulateRecycleView(searchResults) ;
                    }
                });

                // TODO SEARCH PLAYLISTS ( methods are implemented but we need switch button or something)
                // if(something)
                /*
                 networkService.SearchPlaylist(search, new OnDataloadListListener() {
                    public void onDataloadListReady(ArrayList<VideoSearchResult> searchResults) {
                        PopulateRecycleView(searchResults) ;
                    }
                });
                 */
            }
        });

    }

    public void PopulateRecycleView(ArrayList<VideoSearchResult> searchResults){
        searchList = findViewById(R.id.searchList);
        ListAdapterSearch adapter = new ListAdapterSearch(this, searchResults, R.layout.search_view_detail,networkService);
        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
        pgsBar.setVisibility(View.GONE);
    }
}
