package com.artear.youtubemediaapi.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface YoutubeMetadataApiInterface {

    @Headers({"User-Agent:Mozilla/5.0"})
    @GET("get_video_info")
    Call<String> getMetaData(@Query("video_id") String node_id);
}
