package com.artear.app_library_android_youtubemediaapi.main.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.artear.app_library_android_youtubemediaapi.R;
import com.artear.app_library_android_youtubemediaapi.model.YoutubeCover;

import java.util.ArrayList;
import java.util.List;

public class YoutubeListAdapter extends RecyclerView.Adapter {


    private YoutubeListListener youtubeListListener;
    private List<YoutubeCover> list = new ArrayList<>();

    public YoutubeListAdapter(YoutubeListListener youtubeListListener) {
        this.youtubeListListener = youtubeListListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.youtube_list_item,
                parent, false);
        return new YoutubeCoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        YoutubeCoverViewHolder viewHolder = (YoutubeCoverViewHolder) holder;

        final YoutubeCover youtubeCover = list.get(position);

        viewHolder.titleTextView.setText(youtubeCover.getTitle());
        viewHolder.descriptionTextView.setText(youtubeCover.getDescription());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeListListener.onClickYouTubeCover(youtubeCover);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<YoutubeCover> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class YoutubeCoverViewHolder extends RecyclerView.ViewHolder {

        final TextView titleTextView;
        final TextView descriptionTextView;

        YoutubeCoverViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.title_video);
            descriptionTextView = (TextView) itemView.findViewById(R.id.description_video);
        }
    }
}
