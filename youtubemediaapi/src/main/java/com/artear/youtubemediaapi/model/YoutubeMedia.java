package com.artear.youtubemediaapi.model;

public class YoutubeMedia {

    private YoutubeSource[] source;

    public YoutubeMedia(YoutubeSource[] source) {
        this.source = source;
    }

    public YoutubeSource[] getSource() {
        return source;
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
