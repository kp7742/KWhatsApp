package com.kmods;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import static com.kmods.Privacy.JID;
import static com.kmods.Utils.getResID;

public class ModsL extends LinearLayout implements View.OnClickListener {
    private SharedPreferences prefs = getContext().getSharedPreferences("KMODS", 0);
    private SharedPreferences.Editor edit = prefs.edit();
    private Dialog dialog = new Dialog(getContext());

    public ModsL(Context context) {
        super(context);
        this.init();
    }

    public ModsL(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public ModsL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dialog.setTitle("Set Mods");
        dialog.setContentView(Utils.getResID("custom_mods", "layout"));
        final ToggleButton toggle1 = (ToggleButton) dialog.findViewById(getResID("tb6", "id"));
        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edit.putBoolean(JID + "-hideinfo", true);
                    edit.apply();
                } else {
                    edit.putBoolean(JID + "-hideinfo", false);
                    edit.apply();
                }
            }
        });
        toggle1.setChecked(Utils.getBoolean(JID + "-hideinfo"));
        final ToggleButton toggle2 = (ToggleButton) dialog.findViewById(getResID("tb7", "id"));
        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edit.putBoolean(JID + "-txt_select", true);
                    edit.apply();
                } else {
                    edit.putBoolean(JID + "-txt_select", false);
                    edit.apply();
                }
            }
        });
        toggle2.setChecked(Utils.getBoolean(JID + "-txt_select"));
        final ToggleButton toggle3 = (ToggleButton) dialog.findViewById(getResID("tb8", "id"));
        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edit.putBoolean(JID + "-call_btn", true);
                    edit.apply();
                } else {
                    edit.putBoolean(JID + "-call_btn", false);
                    edit.apply();
                }
            }
        });
        toggle3.setChecked(Utils.getBoolean(JID + "-call_btn"));
        dialog.show();
    }
}
