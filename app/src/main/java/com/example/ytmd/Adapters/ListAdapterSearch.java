package com.example.ytmd.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;
import com.google.api.services.youtube.model.SearchResultSnippet;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class ListAdapterSearch extends RecyclerView.Adapter<ListAdapterSearch.ListViewHolder> {

    Context context;
    int layout;
    ArrayList<VideoSearchResult> videoSearchResults;

    public ListAdapterSearch(Context ctx, ArrayList<VideoSearchResult> videoSearchResults, int layout) {
        context = ctx;
        this.videoSearchResults = videoSearchResults;
        this.layout = layout;
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
        holder.text1.setText(video.getTitle());
        holder.text2.setText(video.getDescription());
        holder.img.setImageBitmap(video.getImage());

    }

    @Override
    public int getItemCount() {
        return videoSearchResults.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2;
        ImageView img;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.listItem1);
            text2 = itemView.findViewById(R.id.listItem2);
            img = itemView.findViewById(R.id.image);
        }
    }
}
