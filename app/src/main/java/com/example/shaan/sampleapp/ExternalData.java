package com.example.shaan.sampleapp;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;

/**
 * Created by SHAAN on 20-09-16.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    TextView canWrite, canRead;
    String state;
    Spinner spinner;
    String[] paths = { "Music", "Pictures", "Downloads" };
    boolean canW, canR;
    File path = null;
    File file = null;
    Button confirm, save;
    EditText saveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externalstorage);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        state = Environment.getExternalStorageState();

        saveFile = (EditText) findViewById(R.id.etSaveAs);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,paths);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        checkState();
    }

    private void checkState() {
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            //read and write
            canWrite.setText("true");
            canRead.setText("true");
            canW = true;
            canR = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            //read but can't write
            canWrite.setText("false");
            canRead.setText("true");
            canW = false;
            canR = true;
        } else {
            //can't read and can't write
            canWrite.setText("false");
            canRead.setText("false");
            canW = false;
            canR = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int pos = spinner.getSelectedItemPosition();
        switch(pos) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path,f + ".png");
                checkState();
                if(canW && canR){

                    path.mkdirs();

                    try {
                        InputStream is = getResources().openRawResource(R.raw.iconheart);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this, "File has been saved", Toast.LENGTH_LONG);
                        t.show();

                        //Update Files for the user to use
                        MediaScannerConnection.scanFile(this,
                                new String[] {file.toString()},
                                null,
                                new MediaScannerConnection.OnScanCompletedListener(){

                                    @Override
                                    public void onScanCompleted(String path, Uri uri) {
                                        Toast t = Toast.makeText(ExternalData.this,"scan complete",Toast.LENGTH_SHORT);
                                        t.show();
                                    }
                                });
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
