package com.artear.app_library_android_youtubemediaapi;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.artear.app_library_android_youtubemediaapi.model.YoutubeCover;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class YoutubeIntentService extends IntentService {


    public static final String ACTION_ERROR = "com.artear.youtubemediaapi.action_error";
    public static final String ACTION_SUCCESS = "com.artear.youtubemediaapi.action_success";

    public YoutubeIntentService() {
        super("YoutubeIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Intent intentResult = new Intent();

        try {

            String response = callYoutubeVideos();

            if (response.isEmpty()) {
                intentResult.setAction(ACTION_ERROR);
                sendBroadcast(intentResult);
                return;
            }

            Log.d("YoutubeIntentService", response);


            JSONObject jsonObject = new JSONObject().getJSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject item = jsonArray.getJSONObject(x);
                String id = item.getString("id");
                JSONObject snippet = item.getJSONObject("snippet");
                String title = snippet.getString("title");
                String description = snippet.getString("description");
                YoutubeCover youtubeCover = new YoutubeCover(id, title, description);
            }
            intentResult.setAction(ACTION_SUCCESS);
        } catch (Exception e) {
            intentResult.setAction(ACTION_ERROR);
        }

        sendBroadcast(intentResult);
    }

    private String callYoutubeVideos() throws Exception {

        StringBuilder response = new StringBuilder();

        HttpURLConnection urlConnection;

        Uri.Builder uriBuilder = Uri.parse(Api.BASE_URL).buildUpon();
        uriBuilder.appendQueryParameter("part", "snippet");
        uriBuilder.appendQueryParameter("chart", "mostPopular");
        uriBuilder.appendQueryParameter("regionCode", "AR");
        uriBuilder.appendQueryParameter("maxResults", "25");
        uriBuilder.appendQueryParameter("key", DeveloperKey.GOOGLE_KEY);

        Uri uri = uriBuilder.build();

        URL url = new URL(uri.toString());
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setRequestMethod("GET");

        urlConnection.connect();

        if (urlConnection.getResponseCode() == 200) {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }
}
