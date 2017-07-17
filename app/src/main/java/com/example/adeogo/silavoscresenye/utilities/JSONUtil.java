package com.example.adeogo.silavoscresenye.utilities;

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
    public static JSONObject getChapterListObject(String JSONResponse, int bookIndex){
        JSONObject chapterObject = null;
        try {
            JSONArray jsonArray = new JSONArray(JSONResponse);
            chapterObject = jsonArray.getJSONObject(bookIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chapterObject;
    }

    public static List<String> getVersesList(JSONObject chapterListObject) throws JSONException {
        List<String> verseList = new ArrayList<>();

        int id = 0;
        String verse = chapterListObject.getString(Integer.toString(id));
        while (verse!=null){
            verseList.add(verse);
            id++;
            verse = chapterListObject.getString(Integer.toString(id));
        }
        return verseList;
    }
}
