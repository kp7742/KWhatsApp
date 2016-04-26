package com.kmods;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Settings extends AppCompatActivity {
    public static Context ctx;
    static { Settings.ctx = null; }
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
    public static int getResId(Context context, String str1, String str2) {
        return ctx.getResources().getIdentifier(str1, str2, context.getPackageName());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Settings.ctx == null) {
            Settings.ctx = this.getBaseContext();
        }
        getActionBar().setTitle("KMODS");
        getFragmentManager().beginTransaction().replace(android.R.id.content, new Preference1()).commit();
    }
    public static class Preference1 extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceClickListener {
        public SharedPreferences.Editor editor;
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
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.wm);
            this.editor = Settings.ctx.getSharedPreferences("com.whatsapp_preferences", 0).edit();
            findPreference("rest").setOnPreferenceClickListener(this);
        }

        public boolean onPreferenceClick(final Preference preference) {
            if (preference.getKey().equals("rest")) {
                Mod.RestartApp();
            }
            return false;
        }
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            updatePrefSummary(findPreference(str));
        }
    }

}
