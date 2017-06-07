package com.example.shaan.sampleapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by SHAAN on 20-09-16.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {

    Button update, sqlview;
    EditText sqlname, sqlreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);

        update = (Button) findViewById(R.id.bUpdate);
        sqlview = (Button) findViewById(R.id.bView);
        sqlname = (EditText) findViewById(R.id.etSQLName);
        sqlreg = (EditText) findViewById(R.id.etReg);

        update.setOnClickListener(this);
        sqlview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlname.getText().toString();
                    String reg = sqlreg.getText().toString();
                    RegNo entry = new RegNo(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name,reg);
                    entry.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Sorry!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if(didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Hurrah!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                sqlname.setText("");
                sqlreg.setText("");
                break;
            case R.id.bView:
                Intent i = new Intent("com.example.shaan.calculator.SQLVIEW");
                startActivity(i);
                break;
        }
    }
}
