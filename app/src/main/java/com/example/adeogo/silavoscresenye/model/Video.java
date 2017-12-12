package com.example.adeogo.silavoscresenye.model;

/**
 * Created by Adeogo on 9/30/2017.
 */

public class Video {
    private String Title;
    private String DateUploaded;
    private String Url;
    private String ThumbnailUrl;
    public Video(String title, String dateUploaded, String url, String thumbnailUrl){
        Title = title;
        DateUploaded = dateUploaded;
        Url = url;
        ThumbnailUrl = thumbnailUrl;
    }

    public String getTitle(){
        return Title;
    }

    public String getDateUploaded() {
        return DateUploaded;
    }

    public String getThumbnailUrl() {
        return ThumbnailUrl;
    }

    public String getUrl() {
        return Url;
    }
}

