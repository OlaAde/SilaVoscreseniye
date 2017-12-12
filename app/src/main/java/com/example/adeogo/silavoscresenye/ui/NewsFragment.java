package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.BlogsCursorAdapter;
import com.example.adeogo.silavoscresenye.adapter.BlogsListAdapter;
import com.example.adeogo.silavoscresenye.model.Blog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adeogo on 7/31/2017.
 */

public class NewsFragment extends Fragment implements BlogsListAdapter.BlogsListAdapterOnclickHandler {

    private BlogsListAdapter mBlogsAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mManager;
    private List<Blog> mBlogList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.news_rv);

        mBlogsAdapter = new BlogsListAdapter(getContext(), this);
        mManager = new LinearLayoutManager(getContext());
        setDataNew();
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mBlogsAdapter);
        mBlogsAdapter.swapCursor(mBlogList);
        return rootView;
    }

    private void setDataNew() {
        mBlogList = new ArrayList<>();
        mBlogList.add(new Blog("Спасен, а что дальше?","Jutina", (long) 676366363, "Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь."));


        mBlogList.add(new Blog("Что определяет победителя?","Jutina", (long) 676366363, "Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь."));

        mBlogList.add(new Blog("Христианская семья – что отличает ее от мирской?","Jutina", (long) 676366363, "Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь."));

        mBlogList.add(new Blog("Believe!!!","Jutina", (long) 676366363, "Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь."));

        mBlogList.add(new Blog("Believe!!!","Jutina", (long) 676366363, "Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь."));
    }

    @Override
    public void voidMethod(List<Blog> blogList, int adapterPosition) {
        Intent intent = new Intent(getActivity(), NewsActivity.class);
        startActivity(intent);
    }
}
