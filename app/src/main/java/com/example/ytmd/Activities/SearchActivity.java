package com.example.ytmd.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Adapters.ListAdapterSearch;
import com.example.ytmd.Adapters.OnItemClickListener;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.ViewModels.SearchViewModel;
import com.example.ytmd.ViewModels.ViewModelFactory;
import com.example.ytmd.dagger.AppComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SearchActivity extends AppCompatActivity {
    @Inject
    public ViewModelFactory mViewModelFactory;

    private RecyclerView searchList;
    private ProgressBar pgsBar;
    private SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppComponent.from(this).inject(this);
        searchViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchViewModel.class);

        searchViewModel
                .getSearchResultDataLive()
                .observe(this, results -> PopulateRecycleView(results));

        setContentView(R.layout.activity_search);
        pgsBar = findViewById(R.id.pBar);

        Button searchActivityBtn = findViewById(R.id.button2);
        searchActivityBtn.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.editText);
            pgsBar.setVisibility(v.VISIBLE);

            searchViewModel.SearchVideo(editText.getText().toString());
        });
    }

    public void PopulateRecycleView(ArrayList<VideoSearchResult> searchResults){
        searchList = findViewById(R.id.searchList);
        ListAdapterSearch adapter = new ListAdapterSearch(this,
                searchResults,
                R.layout.search_view_detail,
                item -> searchViewModel.DownloadVideo(item),
                null);

        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
        pgsBar.setVisibility(View.GONE);
    }
}
