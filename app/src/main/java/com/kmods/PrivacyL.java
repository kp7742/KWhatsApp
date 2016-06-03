package com.kmods;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import static com.kmods.Privacy.JID;
import static com.kmods.Utils.getResID;

public class PrivacyL extends LinearLayout implements View.OnClickListener {
    private SharedPreferences prefs = getContext().getSharedPreferences("kmods_privacy", 0);
    private SharedPreferences.Editor edit = prefs.edit();
    private Dialog dialog = new Dialog(getContext());

    public PrivacyL(Context context) {
        super(context);
        this.init();
    }

    public PrivacyL(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public PrivacyL(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PrivacyL(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.init();
    }

    private void init() {
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            dialog.setTitle("Custom Privacy");
            dialog.setContentView(Utils.getResID("custom_privacy", "layout"));
            final ToggleButton toggle1 = (ToggleButton) dialog.findViewById(getResID("tb1", "id"));
            toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(JID + "_HideRead", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(JID + "_HideRead", false);
                        edit.apply();
                    }
                }
            });
        toggle1.setChecked(Privacy.getPrivacyB(JID + "_HideRead"));
            final ToggleButton toggle2 = (ToggleButton) dialog.findViewById(getResID("tb2", "id"));
            toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(JID + "_HideReceipt", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(JID + "_HideReceipt", false);
                        edit.apply();
                    }
                }
            });
        toggle2.setChecked(Privacy.getPrivacyB(JID + "_HideReceipt"));
            final ToggleButton toggle3 = (ToggleButton) dialog.findViewById(getResID("tb3", "id"));
            toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(JID + "_HideCompose", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(JID + "_HideCompose", false);
                        edit.apply();
                    }
                }
            });
        toggle3.setChecked(Privacy.getPrivacyB(JID + "_HideCompose"));
            final ToggleButton toggle4 = (ToggleButton) dialog.findViewById(getResID("tb4", "id"));
            toggle4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(JID + "_HideRecord", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(JID + "_HideRecord", false);
                        edit.apply();
                    }
                }
            });
        toggle4.setChecked(Privacy.getPrivacyB(JID + "_HideRecord"));
            final ToggleButton toggle5 = (ToggleButton) dialog.findViewById(getResID("tb5", "id"));
            toggle5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        edit.putBoolean(JID + "_HidePlay", true);
                        edit.apply();
                    } else {
                        edit.putBoolean(JID + "_HidePlay", false);
                        edit.apply();
                    }
                }
            });
        toggle5.setChecked(Privacy.getPrivacyB(JID + "_HidePlay"));
        dialog.show();
    }
}
