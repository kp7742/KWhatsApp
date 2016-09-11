package com.whatsapp;

import android.preference.PreferenceActivity;
import kmods.Rango;

public class ti extends PreferenceActivity {
    @Override
    protected void onResume() {
        super.onResume();
        Rango.DoColorP(this);
    }
}
