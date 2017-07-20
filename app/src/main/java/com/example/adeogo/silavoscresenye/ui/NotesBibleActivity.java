package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.BuildConfig;
import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.NotesAdapter;
import com.example.adeogo.silavoscresenye.data.NotesContract;
import com.facebook.stetho.Stetho;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.Objects;

public class NotesBibleActivity extends AppCompatActivity implements NotesAdapter.NotesAdapterOnclickHandler {

    private static final int NOTES_LOADER_ID = 1;
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private FloatingActionButton floatingActionButton;

    private String mTitle;
    private String mPreacher;
    private String mContentString;
    private long mDate;
    private int mCursorIndex;

    // Choose an arbitrary request code value
    private static final int RC_SIGN_IN = 123;
    FirebaseAuth mFireBaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_bible);

        mFireBaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null){
                    //signed in
                    String userName = firebaseUser.getDisplayName();
                    Toast.makeText(NotesBibleActivity.this, userName + " is logged in.!!! ", Toast.LENGTH_SHORT).show();
                }else {
                    //signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(!BuildConfig.DEBUG)
                                    .setTheme(R.style.GreenTheme)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                                    new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
        Stetho.initializeWithDefaults(this);
        mAdapter = new NotesAdapter(this, this);
        mRecyclerView = (RecyclerView) findViewById(R.id.notes_rv);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mLayoutManager = new GridLayoutManager(this,3);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<Cursor> loader = loaderManager.getLoader(NOTES_LOADER_ID);
        if (loader == null)
            loaderManager.initLoader(NOTES_LOADER_ID, null, new CursorCallback());
        else
            loaderManager.restartLoader(NOTES_LOADER_ID, null, new CursorCallback());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotesBibleActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void voidMethod(Cursor mCursor, int adapterPosition) {
        mCursor.moveToPosition(adapterPosition);
        mTitle = NotesContract.getStringFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_NOTE_TITLE);
        mPreacher = NotesContract.getStringFromCursor(mCursor,NotesContract.NotesEntry.COLUMN_PREACHER);
        mContentString = NotesContract.getStringFromCursor(mCursor,NotesContract.NotesEntry.COLUMN_NOTE_CONTENT);
        mDate = NotesContract.getLongFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_DATE_CREATED);
        mCursorIndex = NotesContract.getIntFromCursor(mCursor, NotesContract.NotesEntry._ID);
        Intent intent = new Intent(NotesBibleActivity.this, AddNoteActivity.class);
        intent.putExtra("Title", mTitle);
        intent.putExtra("Preacher", mPreacher);
        intent.putExtra("ContentString", mContentString);
        intent.putExtra("DateCreated", mDate);
        intent.putExtra("CursorIndex", mCursorIndex);
        startActivity(intent);
    }

    public class CursorCallback implements LoaderManager.LoaderCallbacks<Cursor> {

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {

            return new CursorLoader(NotesBibleActivity.this,
                    NotesContract.NotesEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);

        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                    mAdapter.swapCursor(data);
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            mAdapter.swapCursor(null);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFireBaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFireBaseAuth.removeAuthStateListener(mAuthStateListener);
    }
}
