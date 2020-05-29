package com.example.ytmd.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Adapters.ListAdapterSearch;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.ViewModels.SearchPlaylistViewModel;
import com.example.ytmd.ViewModels.ViewModelFactory;
import com.example.ytmd.dagger.AppComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchPlaylistActivity extends AppCompatActivity {
    @Inject
    public ViewModelFactory mViewModelFactory;

    private RecyclerView searchList;
    private ProgressBar pgsBar;
    private SearchPlaylistViewModel searchPlaylistViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent.from(this).inject(this);
        searchPlaylistViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchPlaylistViewModel.class);

        final Observer<List<VideoSearchResult>> nameObserver =
                searchResults -> PopulateRecycleView((ArrayList<VideoSearchResult>) searchResults);

        searchPlaylistViewModel.getSearchPlaylistResultDataLive().observe(this, nameObserver);

        setContentView(R.layout.activity_search_playlist);
        pgsBar = findViewById(R.id.pBar);
        Button searchActivityBtn = findViewById(R.id.button2);

        searchActivityBtn.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editText);
            pgsBar.setVisibility(v.VISIBLE);
            searchPlaylistViewModel.SearchPlaylist(editText.getText().toString());
        });

    }

    public void PopulateRecycleView(ArrayList<VideoSearchResult> searchResults){
        searchList = findViewById(R.id.searchList);
        ListAdapterSearch adapter = new ListAdapterSearch(this,
                searchResults,
                R.layout.search_view_detail,
                item -> searchPlaylistViewModel.DownloadPlaylist(item));

        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
        pgsBar.setVisibility(View.GONE);
    }
}
