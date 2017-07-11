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
import com.example.adeogo.silavoscresenye.model.Note;

import java.util.Random;

/**
 * Created by Adeogo on 7/11/2017.
 */

public class NotesAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context mContext;
    private Cursor mCursor = null;

    public NotesAdapter( Context context,NotesAdapterOnclickHandler notesAdapterOnclickHandler){
        mContext = context;
        mClickHandler = notesAdapterOnclickHandler;
    }

    private final NotesAdapterOnclickHandler mClickHandler;
    private Random mRandom = new Random();

    public interface NotesAdapterOnclickHandler{
        void voidMethod(Cursor mCursor, int adapterPosition);
    }

    public class NotesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView mTitleTextView;
        public final TextView mPreacherTextView;
        public final TextView mDateCreatedTextView;
        public final TextView mContentTextView;

        public NotesAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.title_list_tv);
            mPreacherTextView = itemView.findViewById(R.id.preacher_list_tv);
            mDateCreatedTextView = itemView.findViewById(R.id.date_list_tv);
            mContentTextView = itemView.findViewById(R.id.content_list_tv);
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
        View view  = layoutInflater.inflate(R.layout.list_item_note, parent, false);
        return new NotesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String noteTitle = null;
        String preacher = null;
        String noteContent = null;
        String formatStringDate = null;

        if (mCursor!= null){
            mCursor.moveToPosition(position);
            Note note = new Note(mCursor);
            noteTitle = note.getNoteTitle();
            preacher = note.getPreacher();
            noteContent = note.getContentString();
            long dateCreated = note.getDateCreation();
            formatStringDate = DateUtils.getRelativeTimeSpanString(mContext, dateCreated).toString();
        }

        ((NotesAdapterViewHolder) holder).mTitleTextView.setText(noteTitle);
        ((NotesAdapterViewHolder) holder).mPreacherTextView.setText(preacher);
        ((NotesAdapterViewHolder) holder).mContentTextView.setText(noteContent);
        ((NotesAdapterViewHolder) holder).mDateCreatedTextView.setText(formatStringDate);
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
