package com.example.volleymain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RcView extends RecyclerView.Adapter<RcView.VHolder> {

    private List<Model> list;

    public RcView(List<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;



    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_layout,null,false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {

        //holder.imageView.setImageResource(list.get(position).getThumbnailUrl());
        Picasso.get().load(list.get(position).getUrl()).into(holder.imageView);
        holder.textView.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public VHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            textView=itemView.findViewById(R.id.titlee);
        }
    }
}
