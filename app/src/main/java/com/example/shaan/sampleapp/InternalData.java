package com.example.shaan.sampleapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by SHAAN on 20-09-16.
 */
public class InternalData extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataRes;
    Button save, load;
    FileOutputStream fos;
    public static String filename = "InternalString";

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
        try {
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:
                String data = sharedData.getText().toString();
                //Saving data via file
                /*File f = new File(filename);
                try {
                    fos = new FileOutputStream(f);
                    //write some data
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */
                try {
                    fos = openFileOutput(filename,Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bLoad:
                new loadSomeStuff().execute(filename);
                break;
        }
    }

    public class loadSomeStuff extends AsyncTask<String, Integer, String>{

        ProgressDialog dialog;

        protected void onPreExecute() {
            //example of setting up something
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgress(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;
            FileInputStream fis = null;
            for(int i = 0; i < 20; i++){
                publishProgress(5);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();
            try {
                fis = openFileInput(filename);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1) {
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onProgressUpdate(Integer[] progress){
            dialog.incrementProgressBy(progress[0]);
        }

        protected void onPostExecute(String result){
            dataRes.setText(result);
        }
    }
}
