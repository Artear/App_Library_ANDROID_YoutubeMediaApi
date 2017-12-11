package com.artear.youtubemediaapi.network;

import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.YoutubeMetaData;

/**
 * Created by sergiobanares on 11/12/17.
 */

public interface YouTubeMediaApiCallback {
    void onSuccess(YoutubeMetaData youtubeMetaData);

    void onError(YoutubeMediaApiException youtubeMediaApiException);
}
