package com.example.adeogo.silavoscresenye.model;

import android.database.Cursor;

import com.example.adeogo.silavoscresenye.data.NotesContract;

/**
 * Created by Adeogo on 7/31/2017.
 */

public class Blog {

    private String mBlogTitle;
    private String mAuthor;
    private long mDateCreation;
    private String mContentString;

    public Blog(String blogTitle, String author, Long dateCreation, String contentString) {
        mBlogTitle = blogTitle;
        mAuthor = author;
        mDateCreation = dateCreation;
        mContentString = contentString;
    }

    public Blog(Cursor cursor){
        mBlogTitle = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_NOTE_TITLE);
        mAuthor = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_PREACHER);
        mDateCreation = NotesContract.getLongFromCursor(cursor, NotesContract.NotesEntry.COLUMN_DATE_CREATED);
        mContentString = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_NOTE_CONTENT);
    }

    public String getBlogTitle() {
        return mBlogTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getContentString() {
        return mContentString;
    }

    public long getDateCreation(){
        return mDateCreation;
    }

}
