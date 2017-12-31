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
import java.net.URLConnection;
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

    public static String getCalendarResponseFromHttpUrl(String url) throws  IOException{
        URLConnection connection = new URL(url).openConnection();
        InputStream response = connection.getInputStream();

        try {

            Scanner scanner = new Scanner(response);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
        }

    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
//
//
//
//
//        OkHttpClient client = new OkHttpClient();
//        // code request code here
//
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//
//             Log.v("Link to KJV Bible",response.body().string());
//            return response.body().string();



        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }

}
