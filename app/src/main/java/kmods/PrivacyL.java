package kmods;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import static kmods.Privacy.JID;
import static kmods.Privacy.getPrivacyB;
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
        final SwitchCompat toggle = (SwitchCompat) dialog.findViewById(getResID("tb", "id"));
        final SwitchCompat toggle1 = (SwitchCompat) dialog.findViewById(getResID("tb1", "id"));
        final SwitchCompat toggle2 = (SwitchCompat) dialog.findViewById(getResID("tb2", "id"));
        final SwitchCompat toggle3 = (SwitchCompat) dialog.findViewById(getResID("tb3", "id"));
        final SwitchCompat toggle4 = (SwitchCompat) dialog.findViewById(getResID("tb4", "id"));
        final SwitchCompat toggle5 = (SwitchCompat) dialog.findViewById(getResID("tb5", "id"));
        toggle.setChecked(getPrivacyB(JID));
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID, isChecked);
                edit.apply();
                toggle1.setEnabled(isChecked);
                toggle2.setEnabled(isChecked);
                toggle3.setEnabled(isChecked);
                toggle4.setEnabled(isChecked);
                toggle5.setEnabled(isChecked);
                if(isChecked){
                    edit.putBoolean(JID + "_HideRead", getSpecific(JID ,"_HideRead"));
                    toggle1.setChecked(getSpecific(JID ,"_HideRead"));
                    edit.putBoolean(JID + "_HideReceipt", getSpecific(JID,"_HideReceipt"));
                    toggle2.setChecked(getSpecific(JID,"_HideReceipt"));
                    edit.putBoolean(JID + "_HideCompose", getPrivacyB("HideCompose"));
                    toggle3.setChecked(getPrivacyB("HideCompose"));
                    edit.putBoolean(JID + "_HideRecord", getPrivacyB("HideRecord"));
                    toggle4.setChecked(getPrivacyB("HideRecord"));
                    edit.putBoolean(JID + "_HidePlay", getSpecific(JID,"_HidePlay"));
                    toggle5.setChecked(getSpecific(JID,"_HidePlay"));
                    edit.apply();
                }
            }
        });
        if(!toggle.isChecked()){
            edit.putBoolean(JID + "_HideRead", getSpecific(JID ,"_HideRead"));
            edit.putBoolean(JID + "_HideReceipt", getSpecific(JID,"_HideReceipt"));
            edit.putBoolean(JID + "_HideCompose", getPrivacyB("HideCompose"));
            edit.putBoolean(JID + "_HideRecord", getPrivacyB("HideRecord"));
            edit.putBoolean(JID + "_HidePlay", getSpecific(JID,"_HidePlay"));
        }
        toggle1.setChecked(getPrivacyB(JID + "_HideRead"));
        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID + "_HideRead", isChecked);
                edit.apply();
            }
        });
        toggle2.setChecked(getPrivacyB(JID + "_HideReceipt"));
        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID + "_HideReceipt", isChecked);
                edit.apply();
            }
        });
        toggle3.setChecked(getPrivacyB(JID + "_HideCompose"));
        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID + "_HideCompose", isChecked);
                edit.apply();
            }
        });
        toggle4.setChecked(getPrivacyB(JID + "_HideRecord"));
        toggle4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID + "_HideRecord", isChecked);
                edit.apply();
            }
        });
        toggle5.setChecked(getPrivacyB(JID + "_HidePlay"));
        toggle5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                edit.putBoolean(JID + "_HidePlay", isChecked);
                edit.apply();
            }
        });
        if(!toggle.isChecked()){
            toggle1.setEnabled(false);
            toggle2.setEnabled(false);
            toggle3.setEnabled(false);
            toggle4.setEnabled(false);
            toggle5.setEnabled(false);
        }
        dialog.show();
    }
    private Boolean getSpecific(String jid,String type){
        if(jid.contains("g.us")){
            return getPrivacyB("G" + type);
        } else {
            return getPrivacyB("C" + type);
        }
    }
}