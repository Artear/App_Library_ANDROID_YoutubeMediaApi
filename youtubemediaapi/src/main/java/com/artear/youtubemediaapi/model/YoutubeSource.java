package com.artear.youtubemediaapi.model;

public class YoutubeSource {

    private QualityType quality;
    private String url;

    public YoutubeSource(String url) {
        this(url, QualityType.adaptative);
    }

    public YoutubeSource(String url, QualityType quality) {
        this.url = url;
        this.quality = quality;
    }

    public QualityType getQuality() {
        return quality;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "YoutubeSource{" +
                "quality=" + quality +
                ", url='" + url + '\'' +
                '}';
    }
}
