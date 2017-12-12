package com.example.adeogo.silavoscresenye.utilities;

import android.net.Uri;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Adeogo on 7/17/2017.
 */

public class NetworkUtil {

    public static Uri buildURi(String urlString){
        Uri builtUri = Uri.parse(urlString).buildUpon()
                .build();
        return builtUri;
    }
    public static URL buildUrl(String urlString){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(urlString);
        Uri builtUri = builder.build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {

            Log.v("Link to KJV Bible",url.toString());


        OkHttpClient client = new OkHttpClient();
        // code request code here

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
    }

}
