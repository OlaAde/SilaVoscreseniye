package com.example.adeogo.silavoscresenye.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.model.Blog;

import java.util.List;

/**
 * Created by Adeogo on 10/3/2017.
 */

public class BlogsListAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    private List<Blog> mListBlog = null;

    public BlogsListAdapter(Context context, BlogsListAdapter.BlogsListAdapterOnclickHandler blogsAdapterOnclickHandler){
        mContext = context;
        mClickHandler = blogsAdapterOnclickHandler;
    }

    private final BlogsListAdapter.BlogsListAdapterOnclickHandler mClickHandler;

    public interface BlogsListAdapterOnclickHandler {
        void voidMethod(List<Blog> blogList, int adapterPosition);
    }

    public class BlogsListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleTextView;
        public final TextView mDateCreatedTextView;
        public final ImageView mImageView;

        public BlogsListAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.blog_title_tv);
            mDateCreatedTextView = (TextView) itemView.findViewById(R.id.blog_date_tv);
            mImageView = (ImageView) itemView.findViewById(R.id.blog_iv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.voidMethod(mListBlog, adapterPosition);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.list_item_blog, parent, false);
        return new BlogsListAdapter.BlogsListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String blogTitle = null;
        String author = null;
        String blogContent = null;
        String formatStringDate = null;

        if (mListBlog!= null){
            Blog blog = mListBlog.get(position);
            blogTitle = blog.getBlogTitle();
            author = blog.getAuthor();
            blogContent = blog.getContentString();
            long dateCreated = blog.getDateCreation();
            formatStringDate = DateUtils.getRelativeTimeSpanString(mContext, dateCreated).toString();
        }

        ((BlogsListAdapter.BlogsListAdapterViewHolder) holder).mTitleTextView.setText(blogTitle);
        ((BlogsListAdapterViewHolder) holder).mImageView.setImageResource(R.drawable.believe2);
        ((BlogsListAdapter.BlogsListAdapterViewHolder) holder).mDateCreatedTextView.setText(formatStringDate);
    }

    @Override
    public int getItemCount() {
        if(null == mListBlog){
            return 0;
        }
        return mListBlog.size();
    }

    public List<Blog> swapCursor(List<Blog> c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mListBlog == c) {
            return null; // bc nothing has changed
        }
        List<Blog> temp = mListBlog;
        this.mListBlog = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
