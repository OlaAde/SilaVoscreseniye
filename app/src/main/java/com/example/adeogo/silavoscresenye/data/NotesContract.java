package com.example.adeogo.silavoscresenye.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Created by Adeogo on 7/11/2017.
 */

public class NotesContract {

    private NotesContract(){
    }
    public static final String AUTHORITY = "com.example.adeogo.silavoscresenye";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_NOTES = "notes";


    public static final class NotesEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NOTES).build();
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_CONTENT= "note_content";
        public static final String COLUMN_DATE_CREATED = "date_created";
        public static final String COLUMN_PREACHER = "preacher";
    }

    public static String getStringFromCursor(@NonNull Cursor cursor, String columnName){
        String text;
        int cursorColumnIndex = cursor.getColumnIndex(columnName);
        text = cursor.getString(cursorColumnIndex);
        return text;
    }

    public static long getLongFromCursor(@NonNull Cursor cursor, String columnName){
        long longResult;
        int cursorColumnIndex = cursor.getColumnIndex(columnName);
        longResult = cursor.getLong(cursorColumnIndex);
        return longResult;
    }
}
