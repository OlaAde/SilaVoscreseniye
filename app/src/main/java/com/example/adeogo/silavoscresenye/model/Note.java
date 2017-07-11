package com.example.adeogo.silavoscresenye.model;

import android.database.Cursor;

import com.example.adeogo.silavoscresenye.data.NotesContract;

/**
 * Created by Adeogo on 7/11/2017.
 */

public class Note {
    private String mNoteTitle;
    private String mPreacher;
    private long mDateCreation;
    private String mContentString;

    public Note(String noteTitle, String preacher, Long dateCreation, String contentString) {
        mNoteTitle = noteTitle;
        mPreacher = preacher;
        mDateCreation = dateCreation;
        mContentString = contentString;
    }

    public Note(Cursor cursor){
        mNoteTitle = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_NOTE_TITLE);
        mPreacher = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_PREACHER);
        mDateCreation = NotesContract.getLongFromCursor(cursor, NotesContract.NotesEntry.COLUMN_DATE_CREATED);
        mContentString = NotesContract.getStringFromCursor(cursor, NotesContract.NotesEntry.COLUMN_NOTE_CONTENT);
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public String getPreacher() {
        return mPreacher;
    }

    public String getContentString() {
        return mContentString;
    }

    public long getDateCreation(){
        return mDateCreation;
    }


}
