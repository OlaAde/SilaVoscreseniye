package com.example.adeogo.silavoscresenye.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.model.Blog;

/**
 * Created by Adeogo on 7/31/2017.
 */

public class BlogsAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    private Cursor mCursor = null;

    public BlogsAdapter( Context context,BlogsAdapter.BlogsAdapterOnclickHandler blogsAdapterOnclickHandler){
        mContext = context;
        mClickHandler = blogsAdapterOnclickHandler;
    }

    private final BlogsAdapter.BlogsAdapterOnclickHandler mClickHandler;

    public interface BlogsAdapterOnclickHandler{
        void voidMethod(Cursor mCursor, int adapterPosition);
    }

    public class BlogsAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleTextView;
        public final TextView mAuthorTextView;
        public final TextView mDateCreatedTextView;
        public final TextView mContentTextView;

        public BlogsAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.blog_title_tv);
            mAuthorTextView = (TextView) itemView.findViewById(R.id.author_blog_tv);
            mDateCreatedTextView = (TextView) itemView.findViewById(R.id.date_blog_tv);
            mContentTextView = (TextView) itemView.findViewById(R.id.short_content_blog_tv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.voidMethod(mCursor, adapterPosition);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.list_item_blog, parent, false);
        return new BlogsAdapter.BlogsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String blogTitle = null;
        String author = null;
        String blogContent = null;
        String formatStringDate = null;

        if (mCursor!= null){
            mCursor.moveToPosition(position);
            Blog blog = new Blog(mCursor);
            blogTitle = blog.getBlogTitle();
            author = blog.getAuthor();
            blogContent = blog.getContentString();
            long dateCreated = blog.getDateCreation();
            formatStringDate = DateUtils.getRelativeTimeSpanString(mContext, dateCreated).toString();
        }

        ((BlogsAdapter.BlogsAdapterViewHolder) holder).mTitleTextView.setText(blogTitle);
        ((BlogsAdapterViewHolder) holder).mAuthorTextView.setText(author);
        ((BlogsAdapter.BlogsAdapterViewHolder) holder).mContentTextView.setText(blogContent);
        ((BlogsAdapter.BlogsAdapterViewHolder) holder).mDateCreatedTextView.setText(formatStringDate);
    }

    @Override
    public int getItemCount() {
        if(null == mCursor){
            return 0;
        }
        return mCursor.getCount();
    }

    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
