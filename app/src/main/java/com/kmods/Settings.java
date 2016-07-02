package com.kmods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;

import static com.kmods.Utils.getResID;

public class Settings extends AppCompatActivity {
    private static Context sctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sctx = this;
        Utils.SetPrefString("documents", "csv,pdf,txt,doc,docx,xls,xlsx,ppt,pptx,apk,unknown");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsF()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public static class SettingsF extends PreferenceFragment implements Preference.OnPreferenceClickListener {
        @Override
        public void onCreate(Bundle paramBundle) {
            super.onCreate(paramBundle);
            getPreferenceManager().setSharedPreferencesName("KMODS");
            addPreferencesFromResource(Utils.getResID("settings", "xml"));
            findPreference("rest").setOnPreferenceClickListener(this);
            findPreference("backup").setOnPreferenceClickListener(this);
            findPreference("restore").setOnPreferenceClickListener(this);
            findPreference("share").setOnPreferenceClickListener(this);
            findPreference("cshort").setOnPreferenceClickListener(this);
        }
        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference.getKey().equals("rest")) {
                android.os.Process.killProcess(android.os.Process.myPid());
            } else if (preference.getKey().equals("backup")) {
                if (new File(Environment.getDataDirectory(), "data/com.whatsapp").exists()) {
                    new CopyTask(sctx, true, new File(Environment.getDataDirectory(), "data/com.whatsapp"), new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup")).execute(new File[0]);
                } else {
                    Toast.makeText(sctx, "Can't find a Data!", Toast.LENGTH_SHORT).show();
                }
            } else if (preference.getKey().equals("restore")) {
                if (new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup").exists()) {
                    new CopyTask(sctx, false, new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup"), new File(Environment.getDataDirectory(), "data/com.whatsapp")).execute(new File[0]);
                } else {
                    Toast.makeText(sctx, "Can't find a backup in '/sdcard/WhatsApp'!", Toast.LENGTH_SHORT).show();
                }
            } else if (preference.getKey().equals("share")) {
                final String string = sctx.getString(getResID("ShareBdy", "string"));
                final Intent intent3 = new Intent("android.intent.action.SEND");
                intent3.setType("text/plain");
                intent3.putExtra("android.intent.extra.SUBJECT", sctx.getString(getResID("ShareSbj", "string")));
                intent3.putExtra("android.intent.extra.TEXT", string);
                sctx.startActivity(Intent.createChooser(intent3, sctx.getString(getResID("Share", "string"))));
            } else if (preference.getKey().equals("cshort")) {
                Utils.CreateShortcut(sctx);
            }
            return false;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Utils.Auto_restart()) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
