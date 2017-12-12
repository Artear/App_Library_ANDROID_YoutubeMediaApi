package com.artear.youtubemediaapi.network;

import android.support.annotation.NonNull;

import com.artear.youtubemediaapi.YoutubeDecode;
import com.artear.youtubemediaapi.YoutubeErrorType;
import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sergiobanares on 12/12/17.
 */

public class YoutubeMetadataApi {
    YoutubeMetadataApiInterface service;

    public YoutubeMetadataApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.youtube.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(YoutubeMetadataApiInterface.class);
    }


    public void getMetadata(final String node_id, final YouTubeMetadataApiCallback callback) {
        service.getMetaData(node_id).enqueue(new Callback<String>() {
            @Override

            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                try {
                    callback.onSuccess(new YoutubeDecode(node_id, response.body()).parse());
                } catch (YoutubeMediaApiException e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                callback.onError(new YoutubeMediaApiException(YoutubeErrorType.UNKNOWN));
            }
        });
    }
}
