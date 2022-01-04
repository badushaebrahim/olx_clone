package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.*;

import java.util.List;

public class resinf<ViewHolder1> extends RecyclerView.Adapter<resinf.ViewHolder1>{
    private final Context context;
    List<Listing> liste;
    public resinf(List<Listing> liste2, Context context) {

        super();
        this.liste = liste2;
        this.context = context;
    }

    @NonNull
    @Override
    public resinf.ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carvie, parent, false);
        resinf.ViewHolder1 viewHolder = new resinf.ViewHolder1(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull resinf.ViewHolder1 holder, int position) {
//Set data
        final Listing getDataAdapter =  liste.get(position);
        //holder.views.setText(getDataAdapter.getViews());
        holder.videoTitle.setText(getDataAdapter.getHead());
        holder.views.setText(getDataAdapter.getDetail());
        //Picasso.with(context).load(getDataAdapter.getChannel_image()).into(holder.channelImage);
       // picasso.with(context).load(getDataAdapter.getLink()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return liste.size();
        //return 0;
    }
    class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView thumbnail,channelImage;
        TextView videoTitle, views;
        public ViewHolder1(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            //channelImage = itemView.findViewById(R.id.channel_image);
            videoTitle = itemView.findViewById(R.id.video_title);
            views = itemView.findViewById(R.id.det);
        }
    }

}
