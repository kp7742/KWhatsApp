package kmods;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import static kmods.Privacy.JID;
import static kmods.Utils.getResID;

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
    private void init() {
        this.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
            dialog.setTitle("Set Privacy");
            dialog.setContentView(getResID("custom_privacy", "layout"));
            final SwitchCompat toggle1 = (SwitchCompat) dialog.findViewById(getResID("tb1", "id"));
            toggle1.setChecked(Privacy.getPrivacyB(JID + "_HideRead"));
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
            final SwitchCompat toggle2 = (SwitchCompat) dialog.findViewById(getResID("tb2", "id"));
            toggle2.setChecked(Privacy.getPrivacyB(JID + "_HideReceipt"));
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
            final SwitchCompat toggle3 = (SwitchCompat) dialog.findViewById(getResID("tb3", "id"));
            toggle3.setChecked(Privacy.getPrivacyB(JID + "_HideCompose"));
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
            final SwitchCompat toggle4 = (SwitchCompat) dialog.findViewById(getResID("tb4", "id"));
            toggle4.setChecked(Privacy.getPrivacyB(JID + "_HideRecord"));
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
            final SwitchCompat toggle5 = (SwitchCompat) dialog.findViewById(getResID("tb5", "id"));
            toggle5.setChecked(Privacy.getPrivacyB(JID + "_HidePlay"));
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
            dialog.show();
    }
}