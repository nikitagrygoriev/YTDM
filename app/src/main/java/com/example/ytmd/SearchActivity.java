package com.example.ytmd;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;


public class SearchActivity extends AppCompatActivity {


    RecyclerView searchList;
    String[] titles, info;
    int images[] = new int[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Button download = (Button) findViewById(R.id.downloadBtn);
//        download.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent searchIntent = new Intent(getApplicationContext(), PopUp.class);
//                startActivity(searchIntent);
//            }
//        });

        Resources res = getResources();
        Arrays.fill(images, R.drawable.video);
        searchList = findViewById(R.id.searchList);
        titles = res.getStringArray(R.array.titles);
        info = res.getStringArray(R.array.info);
        ListAdapterSearch adapter = new ListAdapterSearch(this, titles, info, images, R.layout.search_view_detail);
        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(this));
    }
}
