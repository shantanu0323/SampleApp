package com.example.shaan.sampleapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by SHAAN on 05-09-16.
 */
public class Prefs extends PreferenceActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
