package com.example.ytmd.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ytmd.Models.MyMusicResult;
import com.example.ytmd.R;

import java.util.ArrayList;
import java.util.List;


public class ListAdapterMyMusic extends RecyclerView.Adapter<ListAdapterMyMusic.ListViewHolder> implements View.OnClickListener{


    private Context ctx;
    private List<MyMusicResult> myMusicResults;
    private int layout;

    public ListAdapterMyMusic(Context ctx, List<MyMusicResult> myMusicResults, int layout ) {

        this.ctx = ctx;
        this.myMusicResults = myMusicResults;
        this.layout = layout;
    }



    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(layout, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        MyMusicResult video = myMusicResults.get(position);
        holder.text1.setText(video  .getTitle());
        holder.img.setImageBitmap(video.getImage());


    }

    @Override
    public int getItemCount() {
        return myMusicResults.size();
    }

    @Override
    public void onClick(View view) {

    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView text1, text2 ,id;
        ImageView img;

        View view;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.listItem1);
            text2 = itemView.findViewById(R.id.listItem2);
            img = itemView.findViewById(R.id.image);
            this.view = view;
        }
    }
}
