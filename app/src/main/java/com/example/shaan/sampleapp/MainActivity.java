package com.example.shaan.sampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter;
    Button add, sub;
    TextView total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counter = 0;
        add = (Button) findViewById(R.id.add1);
        sub = (Button) findViewById(R.id.sub1);
        total = (TextView) findViewById(R.id.tvtotal);

        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                total.setText(" " + counter);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter--;
                total.setText(" " + counter);
            }
        });
    }
}
