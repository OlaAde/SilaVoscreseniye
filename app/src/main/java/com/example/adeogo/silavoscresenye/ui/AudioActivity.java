package com.example.adeogo.silavoscresenye.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.adeogo.silavoscresenye.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;

/**
 * Created by Adeogo on 9/29/2017.
 */

public class AudioActivity extends AppCompatActivity {
    SimpleExoPlayer exoPlayer;
    private String mOfferings = "http://sergeigorokhov.org/dir/";
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        mWebView = (WebView) findViewById(R.id.audio_webview);
        mWebView.setWebViewClient(new AudioActivity.MyBrowser());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mOfferings);
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(request);
            return true;
        }
    }
}
