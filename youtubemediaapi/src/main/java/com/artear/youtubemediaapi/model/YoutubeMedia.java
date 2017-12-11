package com.artear.youtubemediaapi.model;

public class YoutubeMedia {

    YoutubeSource[] source;

    public YoutubeMedia(YoutubeSource[] source) {
        this.source = source;
    }

    @Override
    public String toString() {

        String _return = "YoutubeMedia \n";

        for (YoutubeSource _source :
                source) {
            _return += _source + "\n";
        }
        return _return;
    }
}
