package com.example.adeogo.silavoscresenye.ui;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.data.NotesContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    static String mTitle;
    static String mPreacher;
    static String mContentString;
    static long mDate;

    private static EditText mTitleTextView;
    private static EditText mPreacherTextView;
    private static EditText mContentTextView;

    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        mTitleTextView = (EditText) rootView.findViewById(R.id.add_title_edit_text);
        mPreacherTextView = (EditText) rootView.findViewById(R.id.preacher_edit_text);
        mContentTextView = (EditText) rootView.findViewById(R.id.content_add_string);
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    static public ContentValues saveNote(){
        mTitle = mTitleTextView.getText().toString();
        mPreacher = mPreacherTextView.getText().toString();
        mContentString = mContentTextView.getText().toString();
        mDate = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_TITLE, mTitle);
        contentValues.put(NotesContract.NotesEntry.COLUMN_PREACHER, mPreacher);
        contentValues.put(NotesContract.NotesEntry.COLUMN_DATE_CREATED, mDate);
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_CONTENT, mContentString);

        return contentValues;
    }


}
