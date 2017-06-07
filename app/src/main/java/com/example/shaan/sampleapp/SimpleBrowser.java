package com.example.shaan.sampleapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SHAAN on 20-09-16.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    WebView wv;
    EditText url;
    Button go, back, forward, clear, refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        wv = (WebView) findViewById(R.id.webView);
        wv.setWebViewClient(new ourViewClient());
        wv.loadUrl("http://www.google.com");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setDisplayZoomControls(true);

        try {
            url = (EditText) findViewById(R.id.etUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        go = (Button) findViewById(R.id.bGo);
        back = (Button) findViewById(R.id.bBack);
        refresh = (Button) findViewById(R.id.bRefresh);
        forward = (Button) findViewById(R.id.bForward);
        clear = (Button) findViewById(R.id.bClear);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        forward.setOnClickListener(this);
        clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bGo:
                String website = url.getText().toString();
                wv.loadUrl(website);
                // hiding the keyboard after using the edit text
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            case R.id.bBack:
                if(wv.canGoBack()) {
                    wv.goBack();
                }
                break;
            case R.id.bForward:
                if(wv.canGoForward()) {
                    wv.goForward();
                }
                break;
            case R.id.bRefresh:
                wv.reload();
                break;
            case R.id.bClear:
                wv.clearHistory();
                break;
        }
    }
}
