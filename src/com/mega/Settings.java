package com.mega;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import com.whatsapp.DialogToastPreferenceActivity;

public class Settings extends DialogToastPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    private SharedPreferences.Editor editor;
    static Context ctx;
    public void onBackPressed(){
        Mega.RestartApp();
    }
    private void updatePrefSummary(Preference preference) {
        if (preference != null) {
            String key = preference.getKey();
            if (preference instanceof ListPreference) {
                this.editor.putString(key, ((ListPreference) preference).getValue());
                this.editor.commit();
            } else if (preference instanceof EditTextPreference) {
                preference.setSummary(((EditTextPreference) preference).getText());
            } else if (preference instanceof CheckBoxPreference) {
                this.editor.putBoolean(key, ((CheckBoxPreference) preference).isChecked());
                this.editor.commit();
            }
        }
    }
    @SuppressLint("CommitPrefEdits")
    private static void SetPrefString(final String s1, final String s2) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(Settings.ctx).edit();
        edit.putString(s1, s2);
        edit.commit();
    }
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Settings.ctx == null) {
           Settings.ctx = this.getBaseContext();
        }
        SetPrefString("documents", "pdf,txt,doc,docx,xls,xlsx,ppt,pptx,unknown");
        this.addPreferencesFromResource(this.getResources().getIdentifier("wm", "xml", this.getPackageName()));
        this.editor = ctx.getSharedPreferences("com.whatsapp_preferences", 0).edit();
        findPreference("rest").setOnPreferenceClickListener(this);
    }
    public boolean onPreferenceClick(final Preference preference) {
        if (preference.getKey().equals("rest")){
            Mega.RestartApp();
        }
        return false;
    }
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        updatePrefSummary(findPreference(str));
    }
}