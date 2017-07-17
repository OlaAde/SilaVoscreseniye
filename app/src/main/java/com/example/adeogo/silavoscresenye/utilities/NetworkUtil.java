package com.example.adeogo.silavoscresenye.utilities;

import android.net.Uri;
import android.util.Log;

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

    private static String KJV_ENGLISH = "https://raw.githubusercontent.com/thiagobodruk/bible/master/json/en_kjv.json";
    public static URL buildUrl(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(KJV_ENGLISH);
        Uri builtUri = builder.build();
        URL url = null;
        Log.v("Link to KJV Bible",builtUri.toString());
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
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
