package com.artear.app_library_android_youtubemediaapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.artear.app_library_android_youtubemediaapi.adapter.YoutubeListAdapter;
import com.artear.app_library_android_youtubemediaapi.adapter.YoutubeListListener;
import com.artear.app_library_android_youtubemediaapi.model.YoutubeCover;
import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;
import com.artear.youtubemediaapi.model.YoutubeMetaData;
import com.artear.youtubemediaapi.network.YouTubeMetadataApiCallback;
import com.artear.youtubemediaapi.network.YoutubeMetadataApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements YoutubeListListener {

    private final static String TAG = "MainActivity";
    private YoutubeReceiver rcv;
    private YoutubeListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Default Template */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new YoutubeListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        rcv = new YoutubeReceiver();

        Intent msgIntent = new Intent(this, YoutubeIntentService.class);
        startService(msgIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(YoutubeIntentService.ACTION_ERROR);
        filter.addAction(YoutubeIntentService.ACTION_SUCCESS);
        registerReceiver(rcv, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(rcv);
    }

    @Override
    public void onClickYouTubeCover(YoutubeCover youtubeCover) {
        new YoutubeMetadataApi().getMetadata(youtubeCover.getId(), new YouTubeMetadataApiCallback() {
            @Override
            public void onSuccess(YoutubeMetaData youtubeMetaData) {
                Log.d(TAG, "onSucess");
                Toast.makeText(MainActivity.this, "Lunch Youtube video = "+
                        youtubeMetaData.getYoutubeMedia(), Toast.LENGTH_SHORT).show();
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

    class YoutubeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (YoutubeIntentService.ACTION_SUCCESS.equals(intent.getAction())) {
                ArrayList<YoutubeCover> arrayList = intent.getParcelableArrayListExtra(
                        YoutubeIntentService.YOUTUBE_COVER_LIST);
                adapter.setList(arrayList);
            } else if (YoutubeIntentService.ACTION_ERROR.equals(intent.getAction())) {
                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
