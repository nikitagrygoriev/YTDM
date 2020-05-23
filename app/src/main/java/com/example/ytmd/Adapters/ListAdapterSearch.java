package com.example.ytmd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Models.DownloadRequest;
import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.example.ytmd.Services.AsyncService;

import java.util.ArrayList;


public class ListAdapterSearch extends RecyclerView.Adapter<ListAdapterSearch.ListViewHolder> implements View.OnClickListener{

    Context context;
    int layout;
    private AsyncService videoRepository;
    ArrayList<VideoSearchResult> videoSearchResults;

    public ListAdapterSearch(Context ctx, ArrayList<VideoSearchResult> videoSearchResults, int layout, AsyncService videoRepository) {
        context = ctx;
        this.videoSearchResults = videoSearchResults;
        this.layout = layout;
        this.videoRepository = videoRepository;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        VideoSearchResult video = videoSearchResults.get(position);
        holder.text1.setText(video  .getTitle());
        holder.text2.setText(video.getDescription());
        holder.img.setImageBitmap(video.getImage());

        Button downloadButton = holder.button;

        // TODO
        //  1. add option to change result between video and playlist
        //  2. bind button to download video (implemented below) or playlist (commented)
        //IF VIDEOS
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // download request
                DownloadRequest request = new DownloadRequest(video.getId(),video.getTitle(),video.getImage());
                videoRepository.DownloadVideo(request);

                // POPUP
                CharSequence text = "Downloading \"" + video.getTitle() + "\" ";
                Toast toast = Toast.makeText(v.getContext(), text, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        //ELSE
        /*downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // download request
                 videoRepository.DownloadPlaylist(video.getId(),video.getTitle());

                // POPUP
                CharSequence text = "Downloading \"" + video.getTitle() + "\" ";
                Toast toast = Toast.makeText(v.getContext(), text, Toast.LENGTH_LONG);
                toast.show();

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return videoSearchResults.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2 ,id;
        ImageView img;

        View view;
        Button button;

        public Button getButton() {
            return button;
        }

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.listItem1);
            text2 = itemView.findViewById(R.id.listItem2);
            img = itemView.findViewById(R.id.image);
            button = itemView.findViewById(R.id.downloadBtn);
            this.view = view;
        }
    }
}
