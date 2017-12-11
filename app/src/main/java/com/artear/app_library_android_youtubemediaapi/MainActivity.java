package com.artear.app_library_android_youtubemediaapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.artear.youtubemediaapi.YoutubeDecode;
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

        /*
            void loadMock() throws {
                guard let fileURL = Bundle.main.url(forResource: self.id, withExtension: "txt") else {
                    throw errorType.unknown
                }
                self.content = try String(contentsOf: fileURL, encoding: String.Encoding.utf8).trimmingCharacters(in: .whitespacesAndNewlines)
            }
        */

        loadMock();
    }

    private void loadMock(){
        try {

            String fileName = "file2";
            String article_only_title = TestUtils.loadJSONFromAsset(this, fileName +".txt");
            Log.e(TAG,article_only_title);

            YoutubeDecode decode = new YoutubeDecode(fileName,article_only_title);

            Gson gson = new Gson();
            String json = gson.toJson(decode.parse());
            Log.e(TAG,"parse: \n" + decode.parse());
        }catch (Exception ex){
            Log.e(TAG,"FAIL");
            ex.printStackTrace();
        }
    }
}
