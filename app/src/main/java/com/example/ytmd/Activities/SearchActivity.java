package com.example.ytmd.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Adapters.ListAdapterSearch;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.Repositories.VideoRepository;
import com.example.ytmd.dagger.AppComponent;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;


public class SearchActivity extends AppCompatActivity {

    @Inject
    public   VideoRepository videoRepository;

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
                EditText editText =  (EditText) findViewById(R.id.editText);
                String search = editText.getText().toString();
                pgsBar.setVisibility(v.VISIBLE);
                new SearchOperation().execute(search);
            }
        });


    }

    public void PopulateRecycleView(ArrayList<VideoSearchResult> list){
        searchList = findViewById(R.id.searchList);
        ListAdapterSearch adapter = new ListAdapterSearch(this, list, R.layout.search_view_detail);
        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
        pgsBar.setVisibility(View.GONE);
    }

    private final class SearchOperation extends AsyncTask<String,Void,ArrayList<VideoSearchResult>> {

        @Override
        protected  ArrayList<VideoSearchResult> doInBackground(String... params) {
                try {
                    return  videoRepository.SearchVideos(params[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<VideoSearchResult> result) {
            PopulateRecycleView(result);
        }
    }
}
