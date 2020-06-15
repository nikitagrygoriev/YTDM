package com.example.ytmd.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ytmd.Adapters.PlaylistVideosAdapter;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.ViewModels.SearchPlaylistViewModel;
import com.example.ytmd.ViewModels.ViewModelFactory;
import com.example.ytmd.dagger.AppComponent;

import java.util.ArrayList;

import javax.inject.Inject;


public class PlaylistDetail extends Fragment {

    @Inject
    public ViewModelFactory mViewModelFactory;
    private SearchPlaylistViewModel searchPlaylistViewModel;

    private TextView playlistTitle;
    private RecyclerView searchList;
    private Button playlistDownload;

    public PlaylistDetail() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponent.from(getContext()).inject(this);
        searchPlaylistViewModel = ViewModelProviders.of(this, mViewModelFactory).get(SearchPlaylistViewModel.class);


        searchPlaylistViewModel
                .getPlaylistTitle()
                .observe(this, string -> UpdatePlaylistDetail((String) string));

        searchPlaylistViewModel
                .getSearchPlaylistVideoResultDataLive()
                .observe(this, results -> UpadatePlaylistVideosRecyclier((ArrayList<VideoSearchResult>) results));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        playlistTitle = (TextView) this.getView().findViewById(R.id.playllistTitle);
        searchList =   (RecyclerView) this.getView().findViewById(R.id.playlistVideos);

        playlistDownload = (Button) this.getView().findViewById(R.id.downloadPlaylistBtn);
        playlistDownload.setOnClickListener( click -> DownloadCheckedVideos());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playlist_detail, container, false);
    }

    public void UpdatePlaylistDetail(String state){
        playlistTitle.setText(state);
    }

    public void UpadatePlaylistVideosRecyclier(ArrayList<VideoSearchResult> videos){

        PlaylistVideosAdapter adapter = new PlaylistVideosAdapter(getContext(),
                videos,
                R.layout.playlist_video_item,
                item -> searchPlaylistViewModel.CheckVideoToDownload(item),
                item -> searchPlaylistViewModel.UncheckVideoToDownload(item));

        searchList.setAdapter(adapter);
        searchList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void DownloadCheckedVideos(){
        searchPlaylistViewModel.DownloadCheckedVideos();
    }
}
