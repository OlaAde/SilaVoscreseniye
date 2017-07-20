package com.example.adeogo.silavoscresenye.ui;


import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.data.NotesContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {

    private String mTitle;
    private String mPreacher;
    private String mContentString;
    private long mDate = 0;
    private int mCursorIndex = 0;

    private TextInputEditText mTitleTextView;
    private EditText mPreacherTextView;
    private EditText mContentTextView;

    public NotesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        setHasOptionsMenu(true);
        mTitleTextView = rootView.findViewById(R.id.add_title_edit_text);
        mPreacherTextView = rootView.findViewById(R.id.preacher_edit_text);
        mContentTextView = rootView.findViewById(R.id.content_add_string);
        Intent intent = getActivity().getIntent();
        mTitle = intent.getStringExtra("Title");
        mPreacher = intent.getStringExtra("Preacher");
        mContentString = intent.getStringExtra("ContentString");
        mDate = intent.getLongExtra("DateCreated", System.currentTimeMillis());
        mCursorIndex = intent.getIntExtra("CursorIndex", mCursorIndex);

        if(!TextUtils.isEmpty(mTitle)){
            mTitleTextView.setText(mTitle);
            mPreacherTextView.setText(mPreacher);
            mContentTextView.setText(mContentString);
        }
        return rootView;
    }

    private ContentValues saveNote(){
        mTitle = mTitleTextView.getText().toString();
        mPreacher = mPreacherTextView.getText().toString();
        mContentString = mContentTextView.getText().toString();
        if (mDate == 0)
        mDate = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_TITLE, mTitle);
        contentValues.put(NotesContract.NotesEntry.COLUMN_PREACHER, mPreacher);
        contentValues.put(NotesContract.NotesEntry.COLUMN_DATE_CREATED, mDate);
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_CONTENT, mContentString);

        return contentValues;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_notes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idSelected = item.getItemId();
        switch (idSelected){
            case R.id.action_save:
                ContentValues values = saveNote();
                if (mCursorIndex!= 0){
                    String[] selectionArguments = new String[]{Integer.toString(mCursorIndex)};
                    getContext().getContentResolver().update(NotesContract.NotesEntry.CONTENT_URI,values,NotesContract.NotesEntry._ID + " = ? ", selectionArguments );
                }
                else
                getContext().getContentResolver().insert(NotesContract.NotesEntry.CONTENT_URI,values);

                Toast.makeText(getContext(),values.getAsString(NotesContract.NotesEntry.COLUMN_NOTE_TITLE), Toast.LENGTH_SHORT).show();
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
