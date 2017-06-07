package com.example.shaan.sampleapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by SHAAN on 20-09-16.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataRes;
    Button save, load;
    public static String filename = "MySharedString";
    SharedPreferences spData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setUpVariables();
    }

    private void setUpVariables() {
        save = (Button) findViewById(R.id.bSave);
        load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etData);
        dataRes = (TextView) findViewById(R.id.tvLoadData);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        spData = getSharedPreferences(filename,0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:
                String data = sharedData.getText().toString();
                SharedPreferences.Editor editor = spData.edit();
                editor.putString("sharedString",data);
                editor.commit();
                sharedData.setText("");
                dataRes.setText("");
                break;
            case R.id.bLoad:
                spData = getSharedPreferences(filename,0);
                String loadData = spData.getString("sharedString","Couldn't load data");
                dataRes.setText(loadData);
                break;
        }
    }
}
