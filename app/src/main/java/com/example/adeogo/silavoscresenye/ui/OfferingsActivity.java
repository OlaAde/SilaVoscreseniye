package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.adeogo.silavoscresenye.R;

/**
 * Created by Adeogo on 9/29/2017.
 */

public class OfferingsActivity extends AppCompatActivity {

    private WebView mWebView;
    private String mOfferings = "http://svchurch.ru/svsecuretinkoffpay.php";
    private String mBuilding = "http://buildsvchurch.com/svsecurepay.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offering);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        boolean check = intent.getBooleanExtra("state", true);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new MyBrowser());
        mWebView.getSettings().setJavaScriptEnabled(true);
        if(check){
            mWebView.loadUrl(mOfferings);
        }
        else {
            mWebView.loadUrl(mBuilding);
        }
    }

    private class MyBrowser extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String request) {
            view.loadUrl(request);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
