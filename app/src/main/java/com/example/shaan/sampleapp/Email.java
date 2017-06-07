package com.example.shaan.sampleapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by SHAAN on 30-08-16.
 */
public class Email extends Activity implements View.OnClickListener{
    EditText personsEmail, intro, personsName, stupidThings, hatefulAction, outro;
    String emailAdd, beginning, name, stupidAction, hatefulAct, out;
    Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        intializeVars();
        sendEmail.setOnClickListener(this);
    }

    private void intializeVars() {
        personsEmail = (EditText) findViewById(R.id.etEmails);
        intro = (EditText) findViewById(R.id.etIntro);
        personsName = (EditText) findViewById(R.id.etName);
        stupidThings = (EditText) findViewById(R.id.etThings);
        hatefulAction = (EditText) findViewById(R.id.etAction);
        outro = (EditText) findViewById(R.id.etOutro);
        sendEmail = (Button) findViewById(R.id.bSentEmail);
    }

    public void onClick(View v) {
        convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated();
        String emailaddress[] = {emailAdd};
        String message = "Well hello "
                + name
                + " I just wanted to say "
                + beginning
                + ". Not only that but I hate when you "
                + stupidAction
                + ", that just really makes me crazy. I just want to make a "
                + hatefulAct
                + ". Well, thats all I wanted to chit-chatter about, oh are "
                + out
                + ". Thank You...";
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,emailaddress);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"HERE IS MY MAIL");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
        startActivity(emailIntent);
    }

    private void convertEditTextVarsIntoStringsAndYesThisIsAMethodWeCreated() {
        emailAdd = personsEmail.getText().toString();
        beginning = intro.getText().toString();
        name = personsName.getText().toString();
        stupidAction = stupidThings.getText().toString();
        hatefulAct = hatefulAction.getText().toString();
        out = outro.getText().toString();
    }
}