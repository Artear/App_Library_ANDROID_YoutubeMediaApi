package com.artear.app_library_android_youtubemediaapi;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtils {

    public static String loadJSONFromAsset(Context context, String file) throws Exception {

        StringBuilder buf = new StringBuilder();
        InputStream inputStream = context.getAssets().open(file);
        BufferedReader in =
                new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String str;

        while ((str = in.readLine()) != null) {
            buf.append(str);
        }
        in.close();

        return buf.toString();
    }
}
