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
import com.example.adeogo.silavoscresenye.model.Video;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Adeogo on 9/30/2017.
 */

public class VideoAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    private List<Video> mListVideo = null;

    public VideoAdapter( Context context,VideoAdapter.VideoAdapterOnclickHandler videoAdapterOnclickHandler){
        mContext = context;
        mClickHandler = videoAdapterOnclickHandler;
    }

    private final VideoAdapter.VideoAdapterOnclickHandler mClickHandler;

    public interface VideoAdapterOnclickHandler{
        void voidMethod(List<Video> ListVideo, int adapterPosition);
    }

    public class VideoAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleTextView;
        public final TextView mDateTextView;
        public final ImageView mThumbnailImageView;

        public VideoAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.video_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.video_date);
            mThumbnailImageView = (ImageView) itemView.findViewById(R.id.video_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            mClickHandler.voidMethod(mListVideo, adapterPosition);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view  = layoutInflater.inflate(R.layout.list_item_video, parent, false);
        return new VideoAdapter.VideoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String videoTitle = null;
        String videoDate = null;
        String thumbnailUrl = null;
        String url = null;

        if (mListVideo!= null){
            Video video = mListVideo.get(position);
            videoTitle = video.getTitle();
            videoDate = video.getDateUploaded();
            thumbnailUrl = video.getThumbnailUrl();
            url = video.getUrl();
        }

        ((VideoAdapterViewHolder) holder).mTitleTextView.setText(videoTitle);
        Picasso.with(mContext).load(thumbnailUrl).into(((VideoAdapterViewHolder) holder).mThumbnailImageView);
        ((VideoAdapterViewHolder) holder).mDateTextView.setText(videoDate);
    }

    @Override
    public int getItemCount() {
        if(null == mListVideo){
            return 0;
        }
        return mListVideo.size();
    }

    public List<Video> swapData(List<Video> c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mListVideo == c) {
            return null; // bc nothing has changed
        }
        List<Video> temp = mListVideo;
        this.mListVideo = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
