package com.example.ytmd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapterSearch extends RecyclerView.Adapter<ListAdapterSearch.ListViewHolder> {

    String text1[], text2[];
    Context context;
    int img[];
    int layout;

    public ListAdapterSearch(Context ctx, String s1[], String s2[], int image[], int layout) {
        context = ctx;
        text1 = s1;
        text2 = s2;
        img = image;
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
        holder.text1.setText(text1[position]);
        holder.text2.setText(text2[position]);
        holder.img.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return text1.length;
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
