package com.artear.app_library_android_youtubemediaapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.artear.youtubemediaapi.YoutubeDecode;
import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.YoutubeMetaData;
import com.artear.youtubemediaapi.network.YouTubeMediaApiCallback;
import com.artear.youtubemediaapi.network.YoutubeMediaApi;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Default Template */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        new YoutubeMediaApi().run("qQTVuRrZO8w", new YouTubeMediaApiCallback() {
            @Override
            public void onSuccess(YoutubeMetaData youtubeMetaData) {
                Log.d("MainActivity", "onSucess");

            }

            @Override
            public void onError(YoutubeMediaApiException youtubeMediaApiException) {

                switch (youtubeMediaApiException.getErrorType()) {

                    case UNKNOWN:
                        Log.e("MainActivity", "UNKNOWN");
                        break;
                    case WITHOUTDATA:
                        Log.e("MainActivity", "WITHOUTDATA");
                        break;
                    case WITHQUERYITEMS:
                        Log.e("MainActivity", "WITHQUERYITEMS");
                        break;
                    case SERVER_ERROR:
                        Log.e("MainActivity", "SERVER_ERROR");
                        break;
                }
            }
        });
        //loadMock();
    }

    private void loadMock(){
        try {

            String fileName = "file";
            String article_only_title = TestUtils.loadJSONFromAsset(this, fileName +".txt");
            Log.e(TAG,article_only_title);

            YoutubeDecode decode = new YoutubeDecode(fileName,article_only_title);

            Gson gson = new Gson();
            String json = gson.toJson(decode.parse());
            Log.e(TAG,"parse: \n" + decode.parse());
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
