package com.example.adeogo.silavoscresenye.ui;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

/**
 * Created by Adeogo on 8/27/2017.
 */

public class VideoStreamingActivity extends AppCompatActivity implements ExoPlayer.EventListener{


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mStreamingVideoReference;
    private ChildEventListener mChildEventListener;

    private TextView mTestTextView;
    private Button pushButton;

    private String hlsVideoUri = "http://playertest.longtailvideo.com/adaptive/bbbfull/bbbfull.m3u8";

    private SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private ProgressBar progressBar;

    private boolean autoPlay = false;

    // used to remember the playback position
    private int currentWindow;
    private long playbackPosition;

    // constant fields for saving and restoring bundle
    private static final String AUTOPLAY = "autoplay";
    private static final String CURRENT_WINDOW_INDEX = "current_window_index";
    private static final String PLAYBACK_POSITION = "playback_position";


    private static String mUrlVideo = "http://techslides.com/demos/sample-videos/small.mp4";

    @Override
    protected void onCreate (Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_streaming);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    mPlayerView = (SimpleExoPlayerView) findViewById(R.id.exo_player);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);


    mFirebaseDatabase = FirebaseDatabase.getInstance();
    mStreamingVideoReference = mFirebaseDatabase.getReference().child("video_link");
    mStreamingVideoReference.push().setValue(mUrlVideo);

    mChildEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            String text = dataSnapshot.getValue().toString();
            Toast.makeText(VideoStreamingActivity.this, text, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Toast.makeText(VideoStreamingActivity.this, "deleted!!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    mStreamingVideoReference.addChildEventListener(mChildEventListener);
    pushButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mStreamingVideoReference.push().setValue("www.youtube.com/ii");
        }
    });

//        if (savedInstanceState != null) {
//            playbackPosition = savedInstanceState.getLong(PLAYBACK_POSITION, 0);
//            currentWindow = savedInstanceState.getInt(CURRENT_WINDOW_INDEX, 0);
//            autoPlay = savedInstanceState.getBoolean(AUTOPLAY, false);
//
//    }

        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create a default LoadControl
        LoadControl loadControl = new DefaultLoadControl();
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "Exo2"), defaultBandwidthMeter);
        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // This is the MediaSource representing the media to be played.
        HlsMediaSource hlsMediaSource = new HlsMediaSource(Uri.parse(hlsVideoUri), dataSourceFactory, mainHandler, new AdaptiveMediaSourceEventListener() {
            @Override
            public void onLoadStarted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs) {

            }

            @Override
            public void onLoadCompleted(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {

            }

            @Override
            public void onLoadCanceled(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded) {

            }

            @Override
            public void onLoadError(DataSpec dataSpec, int dataType, int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaStartTimeMs, long mediaEndTimeMs, long elapsedRealtimeMs, long loadDurationMs, long bytesLoaded, IOException error, boolean wasCanceled) {

            }

            @Override
            public void onUpstreamDiscarded(int trackType, long mediaStartTimeMs, long mediaEndTimeMs) {

            }

            @Override
            public void onDownstreamFormatChanged(int trackType, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long mediaTimeMs) {

            }
        });

        mPlayer.addListener(this);
        mPlayer.prepare(hlsMediaSource);
        mPlayerView.requestFocus();
        mPlayer.setPlayWhenReady(true);


    }

    @Override
    public void onTimelineChanged (Timeline timeline, Object manifest){

}

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }



    @Override
    public void onLoadingChanged ( boolean isLoading){

}

    @Override
    public void onPlayerStateChanged ( boolean playWhenReady, int playbackState){

    switch (playbackState) {
        case ExoPlayer.STATE_BUFFERING:
            //You can use progress dialog to show user that video is preparing or buffering so please wait
            progressBar.setVisibility(View.VISIBLE);
            break;
        case ExoPlayer.STATE_IDLE:
            //idle state
            break;
        case ExoPlayer.STATE_READY:
            // dismiss your dialog here because our video is ready to play now
            progressBar.setVisibility(View.GONE);
            break;
        case ExoPlayer.STATE_ENDED:
            // do your processing after ending of video
            break;
    }
}


    @Override
    public void onPlayerError (ExoPlaybackException error){

    AlertDialog.Builder adb = new AlertDialog.Builder(VideoStreamingActivity.this);
    adb.setTitle("Could not able to stream video");
    adb.setMessage("It seems that something is going wrong.\nPlease try again.");
    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            finish(); // take out user from this activity. you can skip this
        }
    });
    AlertDialog ad = adb.create();
    ad.show();
}

    @Override
    public void onPositionDiscontinuity () {

}

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    protected void onPause () {
    super.onPause();
    if (mPlayer != null) {
        mPlayer.setPlayWhenReady(false); //to pause a video because now our video player is not in focus
    }
}

    @Override
    protected void onDestroy () {
    super.onDestroy();
    mPlayer.release();
}

//    private MediaSource buildMediaSource(Uri uri) {
//        DefaultExtractorsFactory extractorSourceFactory = new DefaultExtractorsFactory();
//        DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("ua");
//
//        ExtractorMediaSource audioSource = new ExtractorMediaSource(uri, dataSourceFactory, extractorSourceFactory, null, null);
//
//        // this return a single mediaSource object. i.e. no next, previous buttons to play next/prev media file
//        return new ExtractorMediaSource(uri, dataSourceFactory, extractorSourceFactory, null, null);
//
//    }
//
//    void initializePlayer(String UrlVideo) {
//
//        if(mUrlVideo == null&&mUrlVideo=="")
//            return;
//        // create a new instance of SimpleExoPlayer
//        mPlayer = ExoPlayerFactory.newSimpleInstance(
//                new DefaultRenderersFactory(this),
//                new DefaultTrackSelector(),
//                new DefaultLoadControl());
//
//        // attach the just created player to the view responsible for displaying the media (i.e. media controls, visual feedback)
//        mPlayerView.setPlayer(mPlayer);
//        mPlayer.setPlayWhenReady(autoPlay);
//
//        // resume playback position
//        mPlayer.seekTo(currentWindow, playbackPosition);
//
//        Uri uri = Uri.parse( "http://techslides.com/demos/sample-videos/small.mp4");
//        MediaSource mediaSource = buildMediaSource(uri);
//
//        // now we are ready to start playing our media files
//        mPlayer.prepare(mediaSource);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (Util.SDK_INT > 23) {
//            if(!mUrlVideo.isEmpty())
//            {
//                initializePlayer(mUrlVideo);
//            }
//        }
//    }
//
//    @SuppressLint("InlinedApi")
//    private void hideSystemUi() {
//        View decorView = this.getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
//    }
//
//    private boolean checkIfLandscape(){
//        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
//            return true;
//        else return false;
//    }
//
//    private void releasePlayer() {
//        if (mPlayer != null) {
//            // save the player state before releasing its resources
//            playbackPosition = mPlayer.getCurrentPosition();
//            currentWindow = mPlayer.getCurrentWindowIndex();
//            autoPlay = mPlayer.getPlayWhenReady();
//            mPlayer.release();
//            mPlayer = null;
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (Util.SDK_INT > 23) {
//            initializePlayer(mUrlVideo);
//        }
//        }

//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT <= 23) {
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (Util.SDK_INT > 23) {
//            releasePlayer();
//        }
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        if (mPlayer == null) {
//            outState.putLong(PLAYBACK_POSITION, playbackPosition);
//            outState.putInt(CURRENT_WINDOW_INDEX, currentWindow);
//            outState.putBoolean(AUTOPLAY, autoPlay);
//        }
//    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
