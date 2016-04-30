package com.mega;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.*;
import android.util.Log;
import com.whatsapp.DialogToastPreferenceActivity;

public class Settings extends DialogToastPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
    public SharedPreferences.Editor editor;
    public static Context ctx;
    static { Settings.ctx = null; }
    public Settings() {
        this.editor = null;
    }

    public void onBackPressed(){
        Mega.RestartApp();
    }
    public static void initContextVar(final Context ctx) {
        if (ctx instanceof Activity) {
            Settings.ctx = ctx.getApplicationContext();
        }
        else {
            Settings.ctx = ctx;
        }
        if (Settings.ctx == null) {
            Log.d("KMods", "Context var initialized to NULL!!!");
        }
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
            } else if (preference instanceof TwoStatePreference) {
                this.editor.putBoolean(key, ((TwoStatePreference)preference).isChecked());
                this.editor.commit();
            }
        }
    }
    public static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    public static int getResId(Context context, String str1, String str2) {
        return ctx.getResources().getIdentifier(str1, str2, context.getPackageName());
    }
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (Settings.ctx == null) {
            Settings.ctx = this.getBaseContext();
        }
        this.addPreferencesFromResource(this.getResources().getIdentifier("wm", "xml", this.getPackageName()));
        this.editor = Settings.ctx.getSharedPreferences("com.whatsapp_preferences", 0).edit();
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