package com.example.shaan.sampleapp;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by SHAAN on 20-09-16.
 */
public class ourViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView v, String url) {
        v.loadUrl(url);
        return true;
    }
}
