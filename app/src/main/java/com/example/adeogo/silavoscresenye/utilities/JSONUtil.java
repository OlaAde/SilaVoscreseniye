package com.example.adeogo.silavoscresenye.utilities;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.adeogo.silavoscresenye.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adeogo on 7/17/2017.
 */

public class JSONUtil {

    public static JSONArray getYoutubeArray(@NonNull String JSONResponse){
        JSONArray listArray = null;

        try {

            JSONObject object = new JSONObject(JSONResponse);
            boolean checkBoolean ;
            if(object == null)
                checkBoolean = false;
            else checkBoolean = true;
            Log.v("Checking jjjnj", "Afdfdfnjb"+checkBoolean);
            listArray = object.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listArray;
    }

    public static List<Video> getVideoList(JSONArray jsonArray) throws JSONException {
        List<Video> videoList = new ArrayList<>();
        if (jsonArray == null)
            return null;
        for (int i = 0; i < jsonArray.length(); i++){

            JSONObject itemObject = jsonArray.getJSONObject(i);
            JSONObject idObject = itemObject.getJSONObject("id");
            String videoId = idObject.getString("videoId");
            String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

            JSONObject snippetObject = itemObject.getJSONObject("snippet");
            String dateString = snippetObject.getString("publishedAt");
            String videoTitle = snippetObject.getString("title");

            JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");
            JSONObject defaultObject = thumbnailObject.getJSONObject("default");
            String thumbnailUrl = defaultObject.getString("url");
            Video video = new Video(videoTitle, dateString, videoUrl,thumbnailUrl);

            videoList.add(video);
        }
        return videoList;
    }
}
