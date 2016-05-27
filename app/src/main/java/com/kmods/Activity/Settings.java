package com.kmods.Activity;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.kmods.Utils;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.SetPrefString("documents", "pdf,txt,doc,docx,xls,xlsx,ppt,pptx,unknown");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsF()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static class SettingsF extends PreferenceFragment {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("KMODS");
            addPreferencesFromResource(Utils.getResID("settings", "xml"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
