package com.example.adeogo.silavoscresenye.ui;

import android.content.ContentValues;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.data.NotesContract;

public class AddNoteActivity extends AppCompatActivity {
    String mTitle;
    String mPreacher;
    String mContentString;
    long mDate;

    private EditText mTitleTextView;
    private EditText mPreacherTextView;
    private EditText mContentTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mTitleTextView = (EditText) findViewById(R.id.add_title_edit_text);
        mPreacherTextView = (EditText) findViewById(R.id.preacher_edit_text);
        mContentTextView = (EditText) findViewById(R.id.content_add_string);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void saveNote(){
        mTitle = mTitleTextView.getText().toString();
        mPreacher = mPreacherTextView.getText().toString();
        mContentString = mContentTextView.getText().toString();
        mDate = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_TITLE, mTitle);
        contentValues.put(NotesContract.NotesEntry.COLUMN_PREACHER, mPreacher);
        contentValues.put(NotesContract.NotesEntry.COLUMN_DATE_CREATED, mDate);
        contentValues.put(NotesContract.NotesEntry.COLUMN_NOTE_CONTENT, mContentString);

        getContentResolver().insert(NotesContract.NotesEntry.CONTENT_URI,contentValues);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_notes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idSelected = item.getItemId();
        switch (idSelected){
            case R.id.action_save:
                saveNote();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
