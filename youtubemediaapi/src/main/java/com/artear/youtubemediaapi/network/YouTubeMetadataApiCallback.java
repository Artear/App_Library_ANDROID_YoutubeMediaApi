package com.artear.youtubemediaapi.network;

import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.YoutubeMetaData;

public interface YouTubeMetadataApiCallback {
    void onSuccess(YoutubeMetaData youtubeMetaData);

    void onError(YoutubeMediaApiException youtubeMediaApiException);
}
