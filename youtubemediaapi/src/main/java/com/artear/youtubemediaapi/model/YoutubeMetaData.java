package com.artear.youtubemediaapi.model;

import android.util.Log;

public class YoutubeMetaData {

    private String id;
    private String title = "";
    private String[] keywords;

    private YoutubeMedia youtubeMedia;
    private YoutubeMediaType mediaType = YoutubeMediaType.VIDEO;

    public YoutubeMetaData(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public YoutubeMedia getYoutubeMedia() {
        return youtubeMedia;
    }

    public void setYoutubeMedia(YoutubeMedia youtubeMedia) {
        this.youtubeMedia = youtubeMedia;
    }

    public YoutubeMediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(YoutubeMediaType mediaType) {
        this.mediaType = mediaType;
    }

    private void printModel() {
        Log.d("YoutubeMetaData", "id: " + id);
        Log.d("YoutubeMetaData", "title: " + title);
        Log.d("YoutubeMetaData", "keywords");
        for (String key :
                keywords) {
            Log.d("YoutubeMetaData", "    key: " + key);
        }
        Log.d("YoutubeMetaData", "YoutubeMediaType " + mediaType);
        Log.d("YoutubeMetaData", "youtubeMedia " + youtubeMedia);


    }

    @Override
    public String toString() {
        printModel();
        return super.toString();
    }
}
