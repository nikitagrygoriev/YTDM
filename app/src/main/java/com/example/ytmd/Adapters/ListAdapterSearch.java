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
    ArrayList<VideoSearchResult> videoSearchResults;
    OnItemClickListener onItemClickListener;
    private OnItemClickListener onItemDetailsClickListener;

    public ListAdapterSearch(Context ctx, ArrayList<VideoSearchResult> videoSearchResults,
                             int layout, OnItemClickListener onItemClickListener,  OnItemClickListener onItemDetailsClickListener) {
        context = ctx;
        this.videoSearchResults = videoSearchResults;
        this.layout = layout;
        this.onItemClickListener = onItemClickListener;
        this.onItemDetailsClickListener = onItemDetailsClickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layout, parent, false);
        return new ListViewHolder(view);
    }

    private String FormatTitle(String title){
        if(title.length() > 50){
            return title.substring(0,47) + "...";
        }
        return title;
    }
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        VideoSearchResult video = videoSearchResults.get(position);
        holder.text1.setText(FormatTitle(video .getTitle()));
        holder.img.setImageBitmap(video.getImage());

        Button downloadButton = holder.button;

        downloadButton.setOnClickListener(v -> {
            onItemClickListener.onItemClick(video);
            // POPUP
            CharSequence text = "Downloading \"" + video.getTitle() + "\" ";
            Toast toast = Toast.makeText(v.getContext(), text, Toast.LENGTH_LONG);
            toast.show();
        });

        if(onItemDetailsClickListener != null){
            Button detailButton = holder.detailButton;

            detailButton.setOnClickListener(v -> {
                onItemDetailsClickListener.onItemClick(video);
                // POPUP
               // CharSequence text = "Downloading \"" + video.getTitle() + "\" ";
                //Toast toast = Toast.makeText(v.getContext(), text, Toast.LENGTH_LONG);
                //toast.show();
            });
        }
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

        Button detailButton;

        public Button getButton() {
            return button;
        }

        public Button getDetailButton() {
            return button;
        }

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.listItem1);
            img = itemView.findViewById(R.id.image);
            button = itemView.findViewById(R.id.downloadBtn);
            detailButton = itemView.findViewById(R.id.detailsBtn);
            this.view = view;
        }
    }
}
