package com.example.adeogo.silavoscresenye.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

/**
 * Created by Adeogo on 10/8/2017.
 */

public class ViewVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener  {

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;

    // autoplay = false
    private boolean autoPlay = false;

    // used to remember the playback position
    private int currentWindow;
    private long playbackPosition;

    // constant fields for saving and restoring bundle
    private static final String AUTOPLAY = "autoplay";
    private static final String CURRENT_WINDOW_INDEX = "current_window_index";
    private static final String PLAYBACK_POSITION = "playback_position";

    private Context mContext;

    private static String mUrlVideo = null;

    private static final String TAG = "YoutubeActivity";
    static final String GOOGLE_API_KEY = "AIzaSyD23256QG-T5lb2B9R3CkxRKaNaXLBafW8";
    private String YOUTUBE_VIDEO_ID = "ehAWl0OG2uQ";
    static final String YOUTUBE_PLAYLIST = "RDehAWl0OG2uQ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video);
        mContext = this;

        Intent intent = getIntent();
        String videoUrl = intent.getStringExtra("video_url");
        mUrlVideo = videoUrl;
        YOUTUBE_VIDEO_ID = videoUrl;


        ConstraintLayout layout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_view_video, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);

        playerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        Log.d(TAG, "onInitializationSuccess: provider is " +  provider.getClass().toString());
        Toast.makeText(this, "Initilized Youtube Player successfully", Toast.LENGTH_LONG).show();

        if (!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if (youTubeInitializationResult.isUserRecoverableError()){


            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show();
        } else {
            String errorMessage = String.format("There was an error initialising the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
