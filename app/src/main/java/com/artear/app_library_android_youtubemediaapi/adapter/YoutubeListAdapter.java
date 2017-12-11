package com.artear.app_library_android_youtubemediaapi.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.artear.app_library_android_youtubemediaapi.model.YoutubeCover;

import java.util.List;

public class YoutubeListAdapter extends RecyclerView.Adapter {


    private YoutubeListListener youtubeListListener;
    private List<YoutubeCover> list;

    public YoutubeListAdapter(YoutubeListListener youtubeListListener) {
        this.youtubeListListener = youtubeListListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void setList(List<YoutubeCover> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
