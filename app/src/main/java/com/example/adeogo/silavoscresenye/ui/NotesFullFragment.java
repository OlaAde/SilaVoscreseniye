package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class NotesFullFragment extends Fragment implements NotesAdapter.NotesAdapterOnclickHandler {

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_notes_bible, container, false);


        Stetho.initializeWithDefaults(getContext());
        mAdapter = new NotesAdapter(getContext(), NotesFullFragment.this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.notes_rv);
        floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        Loader<Cursor> loader = loaderManager.getLoader(NOTES_LOADER_ID);
        if (loader == null)
            loaderManager.initLoader(NOTES_LOADER_ID, null, new CursorCallback());
        else
            loaderManager.restartLoader(NOTES_LOADER_ID, null, new CursorCallback());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNoteActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void voidMethod(Cursor mCursor, int adapterPosition) {
        mCursor.moveToPosition(adapterPosition);
        mTitle = NotesContract.getStringFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_NOTE_TITLE);
        mPreacher = NotesContract.getStringFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_PREACHER);
        mContentString = NotesContract.getStringFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_NOTE_CONTENT);
        mDate = NotesContract.getLongFromCursor(mCursor, NotesContract.NotesEntry.COLUMN_DATE_CREATED);
        mCursorIndex = NotesContract.getIntFromCursor(mCursor, NotesContract.NotesEntry._ID);
        Intent intent = new Intent(getContext(), AddNoteActivity.class);
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

            return new CursorLoader(getContext(),
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

}
