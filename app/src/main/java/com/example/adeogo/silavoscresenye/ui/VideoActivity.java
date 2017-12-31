package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.VideoAdapter;
import com.example.adeogo.silavoscresenye.model.Video;
import com.example.adeogo.silavoscresenye.utilities.JSONUtil;
import com.example.adeogo.silavoscresenye.utilities.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Adeogo on 10/1/2017.
 */

public class VideoActivity extends AppCompatActivity implements VideoAdapter.VideoAdapterOnclickHandler {

    private static final String BASE_URL = "https://www.googleapis.com/youtube/v3/search?order=date&part=snippet&channelId=UCYjTuPtp6L43DRrglAis56w&maxResults=25&key=AIzaSyD23256QG-T5lb2B9R3CkxRKaNaXLBafW8";
    private RecyclerView mRecyclerView;
    private VideoAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<Video> mVideoList;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.video_rv);
        mAdapter = new VideoAdapter(this, this);
        mManager = new LinearLayoutManager(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(mManager);

        VideoTask videoTask = new VideoTask();
        videoTask.execute(BASE_URL);


    }

    @Override
    public void voidMethod(List<Video> ListVideo, int adapterPosition) {
        Video video = ListVideo.get(adapterPosition);

        Intent intent = new Intent(this, ViewVideoActivity.class);
        intent.putExtra("video_url", video.getUrl());
        startActivity(intent);
    }

    private class VideoTask extends AsyncTask<String, Void, List<Video>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Video> doInBackground(String... params) {
            List<Video> videoList = null;
            JSONArray array = null;
            try {
                String jsonResponse = NetworkUtil.getCalendarResponseFromHttpUrl(BASE_URL);
                array = JSONUtil.getYoutubeArray(jsonResponse);

                if (array != null){
                    videoList = JSONUtil.getVideoList(array);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return videoList;
        }

        @Override
        protected void onPostExecute(List<Video> videos) {
            super.onPostExecute(videos);
            mProgressBar.setVisibility(View.GONE);
            mAdapter.swapData(videos);
        }
    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
