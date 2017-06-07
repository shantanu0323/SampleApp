package com.example.shaan.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by SHAAN on 19-09-16.
 */
public class Tabs extends Activity implements View.OnClickListener {

    TabHost th;
    TextView showRes;
    TabHost.TabSpec specs;
    Button newTab, bStart, bStop;
    long start, stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        newTab = (Button) findViewById(R.id.bAddTab);
        bStart = (Button) findViewById(R.id.bStartWatch);
        bStop = (Button) findViewById(R.id.bStopWatch);

        showRes = (TextView) findViewById(R.id.tvShowResults);
        start = 0;
        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);
        specs = th.newTabSpec("tab1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);
        specs = th.newTabSpec("tab2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);
        specs = th.newTabSpec("tab3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bAddTab:
                TabHost.TabSpec newSpec = th.newTabSpec("newTab");
                newSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You've created a new Tab");
                        return text;
                    }
                });
                newSpec.setIndicator("New");
                th.addTab(newSpec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();
                if(start != 0) {
                    long res = stop - start;
                    int millis = (int) res;
                    int sec = (int) res / 1000;
                    int min = sec / 60;
                    millis = millis % 1000;
                    sec = sec % 60;
                    showRes.setText(String.format("%d:%02d:%02d",min,sec,millis));
                }
                break;
        }
    }
}
