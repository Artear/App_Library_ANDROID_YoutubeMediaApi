package com.artear.youtubemediaapi.network;

import android.os.AsyncTask;
import android.util.Log;

import com.artear.youtubemediaapi.YoutubeDecode;
import com.artear.youtubemediaapi.exception.YoutubeMediaApiException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class YoutubeMediaApi {

    private YouTubeMediaApiCallback youTubeMediaApiCallback;
    private String id = "";

    public void run(String id, YouTubeMediaApiCallback youTubeMediaApiCallback) {
        this.id = id;
        this.youTubeMediaApiCallback = youTubeMediaApiCallback;

        new RequestTask().execute("https://www.youtube.com/get_video_info");

    }


    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {


            String response = "";

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            String query_url = uri[0] + "?video_id=" + id;
            try {
                URL url = new URL(query_url);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();


                // If the request was successful (response code 200),
                // then read the input stream and parse the response.
                if (urlConnection.getResponseCode() == 200) {

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream()));

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                } else {
                    Log.e("RequestTask", "Error response code: " + urlConnection.getResponseCode());
                }

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception ignored) {

            }


            return response;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.d("YoutubeMediaApi", "result: " + result);
            super.onPostExecute(result);
            try {
                youTubeMediaApiCallback.onSuccess(new YoutubeDecode(id, result).parse());
            } catch (YoutubeMediaApiException ex) {
                youTubeMediaApiCallback.onError(ex);
            }

            //Do anything with response..
        }
    }
}
