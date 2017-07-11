package com.example.adeogo.silavoscresenye.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.adeogo.silavoscresenye.data.NotesContract.NotesEntry;
/**
 * Created by Adeogo on 7/11/2017.
 */

public class NotesHelper extends SQLiteOpenHelper{
    // The name of the database
    private static final String DATABASE_NAME = "notesDb.db";

    // If you change the database schema, you must increment the database version
    private static final int VERSION = 1;
    public NotesHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE "  + NotesEntry.TABLE_NAME + " (" +
                NotesEntry._ID                + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NotesEntry.COLUMN_NOTE_TITLE + " TEXT NOT NULL, " +
                NotesEntry.COLUMN_PREACHER    + " TEXT, " +
                NotesEntry.COLUMN_DATE_CREATED + " LONG NOT NULL, " +
                NotesEntry.COLUMN_NOTE_CONTENT + " LONG NOT NULL);";
        Log.v("Create_State,ent", CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
