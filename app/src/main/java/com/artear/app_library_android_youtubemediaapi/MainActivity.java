package com.artear.app_library_android_youtubemediaapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.YoutubeMetaData;
import com.artear.youtubemediaapi.network.YouTubeMetadataApiCallback;
import com.artear.youtubemediaapi.network.YoutubeMetadataApi;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Default Template */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        YoutubeMetadataApi api = new YoutubeMetadataApi();
        api.getMetadata("sTiKv0rK9OE", new YouTubeMetadataApiCallback() {
            @Override
            public void onSuccess(YoutubeMetaData youtubeMetaData) {
                Log.d(TAG, "onSucess");
            }

            @Override
            public void onError(YoutubeMediaApiException youtubeMediaApiException) {

                switch (youtubeMediaApiException.getErrorType()) {

                    case UNKNOWN:
                        Log.e(TAG, "UNKNOWN");
                        break;
                    case WITHOUTDATA:
                        Log.e(TAG, "WITHOUTDATA");
                        break;
                    case WITHQUERYITEMS:
                        Log.e(TAG, "WITHQUERYITEMS");
                        break;
                    case SERVER_ERROR:
                        Log.e(TAG, "SERVER_ERROR");
                        break;
                }
            }
        });
    }

}
