package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.NotesAdapter;
import com.example.adeogo.silavoscresenye.data.NotesContract;
import com.facebook.stetho.Stetho;

import java.util.Objects;

public class NotesBibleActivity extends AppCompatActivity implements NotesAdapter.NotesAdapterOnclickHandler {

    private static final int NOTES_LOADER_ID = 1;
    private RecyclerView mRecyclerView;
    private NotesAdapter mAdapter;
    private GridLayoutManager mLayoutManager;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_bible);
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
}
