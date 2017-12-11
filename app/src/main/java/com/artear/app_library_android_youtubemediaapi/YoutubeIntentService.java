package com.artear.app_library_android_youtubemediaapi;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


public class YoutubeIntentService extends IntentService {


    public static final String ACTION_ERROR = "com.artear.youtubemediaapi.action_error";
    public static final String ACTION_SUCCESS = "com.artear.youtubemediaapi.action_success";

    public YoutubeIntentService(){
        super("YoutubeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


    }
}
