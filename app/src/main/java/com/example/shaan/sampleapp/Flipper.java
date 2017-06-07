package com.example.shaan.sampleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

/**
 * Created by SHAAN on 20-09-16.
 */
public class Flipper extends Activity implements View.OnClickListener {

    ViewFlipper flippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);

        flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
        flippy.setOnClickListener(this);
        flippy.setFlipInterval(1000);
        flippy.startFlipping();
    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
