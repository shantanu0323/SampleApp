package com.example.shaan.sampleapp;

import android.app.Activity;
import android.app.Dialog;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Created by SHAAN on 20-09-16.
 */
public class SQLView extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlview);
        tv = (TextView)findViewById(R.id.tvSQLinfo);
        RegNo info = new RegNo(this);
        String data = "";
        try {
            info.open();
            data = info.getData();
            info.close();
        } catch (SQLException e) {
            e.printStackTrace();
            String error = e.toString();
            Dialog d = new Dialog(this);
            d.setTitle("Sorry!");
            TextView tv = new TextView(this);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        } finally {
            tv.setText(data);
        }
    }
}
