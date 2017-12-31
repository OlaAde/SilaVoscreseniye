package com.example.adeogo.silavoscresenye.utilities;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.adeogo.silavoscresenye.model.CalendarEvent;
import com.example.adeogo.silavoscresenye.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.SoftReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Adeogo on 7/17/2017.
 */

public class JSONUtil {

    public static List<CalendarEvent> getCalendarEventList(@NonNull String JSONResponse){
        JSONArray listArray = null;
        List<CalendarEvent> calendarEventList = new ArrayList<>();

        try {

            JSONObject object = new JSONObject(JSONResponse);

            listArray = object.getJSONArray("items");


            for (int i = 0; i < listArray.length(); i++){

                JSONObject itemObject = listArray.getJSONObject(i);

                String eventSummary = itemObject.getString("summary");

                JSONObject startObject = itemObject.getJSONObject("start");
                String startTime = startObject.getString("dateTime");

                JSONObject endObject = itemObject.getJSONObject("end");
                String endTime = endObject.getString("dateTime");

                CalendarEvent calendarEvent = new CalendarEvent(eventSummary, startTime, endTime);

                calendarEventList.add(calendarEvent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return calendarEventList;

    }

    public static JSONArray getYoutubeArray(@NonNull String JSONResponse){
        JSONArray listArray = null;

        try {

            JSONObject object = new JSONObject(JSONResponse);
            boolean checkBoolean ;
            if(object == null)
                checkBoolean = false;
            else checkBoolean = true;
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

            String kind = idObject.getString("kind");
            if (TextUtils.equals(kind, "youtube#video")){
                String videoId = idObject.getString("videoId");
                String videoUrl = "https://www.youtube.com/watch?v=" + videoId;

                JSONObject snippetObject = itemObject.getJSONObject("snippet");
                String dateString = snippetObject.getString("publishedAt");
                String videoTitle = snippetObject.getString("title");

                JSONObject thumbnailObject = snippetObject.getJSONObject("thumbnails");
                JSONObject defaultObject = thumbnailObject.getJSONObject("default");
                String thumbnailUrl = defaultObject.getString("url");
                Video video = new Video(videoTitle, dateString, videoId,thumbnailUrl);

                videoList.add(video);
            }
        }

        return videoList;
    }

    public static long getUnixTime(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dateString);

        long unixTime = date.getTime();
        return unixTime;
    }
}