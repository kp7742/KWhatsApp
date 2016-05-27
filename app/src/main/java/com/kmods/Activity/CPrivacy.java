package com.kmods.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.kmods.Privacy;

import static com.kmods.Utils.getResID;

public class CPrivacy extends AppCompatActivity {
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = this;
        edit = ctx.getSharedPreferences("kmods_privacy", 0).edit();
        setContentView(getResID("custom_privacy", "layout"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Toast.makeText(this , "This JabberID Is " + Privacy.JID + ".", Toast.LENGTH_SHORT).show();
        final ToggleButton toggle1 = (ToggleButton) findViewById(getResID("tb1","id"));
        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(Privacy.JID + "_HideRead", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(Privacy.JID + "_HideRead", false);
                        edit.apply();
                    }
            }
        });
        final ToggleButton toggle2 = (ToggleButton) findViewById(getResID("tb2","id"));
        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(Privacy.JID + "_HideCompose", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(Privacy.JID + "_HideCompose", false);
                        edit.apply();
                    }
            }
        });
        final ToggleButton toggle3 = (ToggleButton) findViewById(getResID("tb3","id"));
        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(Privacy.JID + "_HideRecord", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(Privacy.JID + "_HideRecord", false);
                        edit.apply();
                    }
            }
        });
        final ToggleButton toggle4 = (ToggleButton) findViewById(getResID("tb4","id"));
        toggle4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(Privacy.JID + "_HidePlay", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(Privacy.JID + "_HidePlay", false);
                        edit.apply();
                    }
            }
        });
        if(Privacy.JID == null) {
            Toast.makeText(ctx, "You Didn't Select Any Contact", Toast.LENGTH_SHORT).show();
            toggle1.setVisibility(View.GONE);
            toggle2.setVisibility(View.GONE);
            toggle3.setVisibility(View.GONE);
            toggle4.setVisibility(View.GONE);
        }
        toggle1.setChecked(Privacy.getPrivacyB(Privacy.JID + "_HideRead"));
        toggle2.setChecked(Privacy.getPrivacyB(Privacy.JID + "_HideCompose"));
        toggle3.setChecked(Privacy.getPrivacyB(Privacy.JID + "_HideRecord"));
        toggle4.setChecked(Privacy.getPrivacyB(Privacy.JID + "_HidePlay"));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
