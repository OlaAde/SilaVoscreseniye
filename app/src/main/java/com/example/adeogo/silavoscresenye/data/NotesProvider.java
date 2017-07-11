package com.example.adeogo.silavoscresenye.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.example.adeogo.silavoscresenye.data.NotesContract.NotesEntry;

/**
 * Created by Adeogo on 7/11/2017.
 */

public class NotesProvider extends ContentProvider{
    public static final int NOTES = 100;
    public static final int NOTE_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private NotesHelper mNotesDbHelper;


    public static UriMatcher buildUriMatcher() {

        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        /*
          All paths added to the UriMatcher have a corresponding int.
          For each kind of uri you may want to access, add the corresponding match with addURI.
          The two calls below add matches for the task directory and a single item by ID.
         */
        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES, NOTES);
        uriMatcher.addURI(NotesContract.AUTHORITY, NotesContract.PATH_NOTES + "/#", NOTE_WITH_ID);

        return uriMatcher;
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        mNotesDbHelper = new NotesHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        final SQLiteDatabase db =  mNotesDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;
        switch (match){
            case NOTES:
                retCursor =  db.query(NotesEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case NOTE_WITH_ID:
                String normalizedIdString = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{normalizedIdString};
                retCursor =  db.query(NotesEntry.TABLE_NAME,projection,NotesContract.NotesEntry._ID + " = ? ",selectionArguments,null,null,null);
                break;
            // Default exception
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        // Get access to the task database (to write new data to)
        final SQLiteDatabase db = mNotesDbHelper.getWritableDatabase();

        // Write URI matching code to identify the match for the tasks directory
        int match = sUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case NOTES:
                // Insert new values into the database
                // Inserting values into tasks table
                long id = db.insert(NotesEntry.TABLE_NAME, null, contentValues);

                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(NotesEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            // Set the value for the returnedUri and write the default case for unknown URI's
            // Default case throws an UnsupportedOperationException
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        // Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notifyChange(uri, null);

        // Return constructed uri (this points to the newly inserted row of data)
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        /* Users of the delete method will expect the number of rows deleted to be returned. */
        int numRowsDeleted;
        if (null == selection) selection = "1";

        switch (sUriMatcher.match(uri)) {

            case NOTES:
                numRowsDeleted = mNotesDbHelper.getWritableDatabase().delete(
                        NotesEntry.TABLE_NAME,
                        selection,
                        selectionArgs);

                break;
            case NOTE_WITH_ID:
                String normalizedIdString = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{normalizedIdString};
                numRowsDeleted = mNotesDbHelper.getWritableDatabase().delete(NotesEntry.TABLE_NAME,NotesContract.NotesEntry._ID + " = ? ",selectionArguments);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        /* If we actually deleted any rows, notify that a change has occurred to this URI */
        if (numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        int numOfRowsChanged;
        switch (match) {

            case NOTES:
                numOfRowsChanged = mNotesDbHelper.getWritableDatabase().update(NotesEntry.TABLE_NAME,
                        values, selection, selectionArgs);
                break;
            case NOTE_WITH_ID:
                String normalizedIdString = uri.getLastPathSegment();
                String[] selectionArguments = new String[]{normalizedIdString};
                numOfRowsChanged = mNotesDbHelper.getWritableDatabase().update(NotesEntry.TABLE_NAME, values,NotesContract.NotesEntry._ID + " = ? ",selectionArguments);
                break;
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);

        }

        if(numOfRowsChanged !=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }

        return numOfRowsChanged;

    }

    @Override
    public int bulkInsert(@NonNull Uri uri,  ContentValues[] values) {
        final SQLiteDatabase db = mNotesDbHelper.getWritableDatabase();

        switch (sUriMatcher.match(uri)) {

            case NOTES:
                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NotesEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            rowsInserted++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }

                if (rowsInserted > 0) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }

                return rowsInserted;

            default:
                return super.bulkInsert(uri, values);
        }
    }
}
