package com.example.adeogo.silavoscresenye.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.BlogsAdapter;
import com.example.adeogo.silavoscresenye.adapter.NotesAdapter;
import com.example.adeogo.silavoscresenye.data.NotesContract;

/**
 * Created by Adeogo on 7/31/2017.
 */

public class BlogsFragment extends Fragment implements BlogsAdapter.BlogsAdapterOnclickHandler {

    private static final int BLOGS_LOADER_ID = 1;
    private RecyclerView mRecyclerView;
    private BlogsAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    private String mTitle;
    private String mAuthor;
    private String mContentString;
    private long mDate;
    private int mCursorIndex;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blogs, container, false);

        mAdapter = new BlogsAdapter(getContext(), BlogsFragment.this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.blogs_rv);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);
        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        Loader<Cursor> loader = loaderManager.getLoader(BLOGS_LOADER_ID);
        if (loader == null)
            loaderManager.initLoader(BLOGS_LOADER_ID, null, new BlogsFragment.CursorCallback());
        else
            loaderManager.restartLoader(BLOGS_LOADER_ID, null, new BlogsFragment.CursorCallback());

        return rootView;
    }

    @Override
    public void voidMethod(Cursor mCursor, int adapterPosition) {

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
