package com.example.ytmd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Models.VideoSearchResult;
import com.example.ytmd.R;

import java.util.ArrayList;


public class PlaylistVideosAdapter extends RecyclerView.Adapter<PlaylistVideosAdapter.ListViewHolder> implements View.OnClickListener{

    Context context;
    int layout;
    private OnItemClickListener addItem;
    private OnItemClickListener removeItem;
    ArrayList<VideoSearchResult> videoSearchResults;
    OnItemClickListener onItemClickListener;


    public PlaylistVideosAdapter(Context ctx, ArrayList<VideoSearchResult> videoSearchResults,
                                 int layout, OnItemClickListener addItem, OnItemClickListener removeItem) {
        context = ctx;
        this.videoSearchResults = videoSearchResults;
        this.layout = layout;
        this.addItem = addItem;
        this.removeItem = removeItem;
        this.onItemClickListener = onItemClickListener;
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
        holder.id.setText(FormatTitle(video .getId()));

        addItem.onItemClick(video);

        CheckBox checkBox = holder.checkBox;
        checkBox.setChecked(video.getChecked());
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                video.setChecked(true);
                addItem.onItemClick(video);
            }else{
                removeItem.onItemClick(video);
                video.setChecked(false);

            }
        }
        );
    }

    @Override
    public int getItemCount() {
        return videoSearchResults.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView text1;

        CheckBox checkBox;

        View view;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.playlistVideoId);
            text1 = itemView.findViewById(R.id.listItem1);
            checkBox = itemView.findViewById(R.id.checkBox);
            this.view = view;
            this.setIsRecyclable(false);
        }
    }
}
