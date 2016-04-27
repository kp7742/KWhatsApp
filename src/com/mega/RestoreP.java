package com.mega;

import android.content.Context;
import android.os.Environment;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.AttributeSet;
import android.widget.Toast;

import java.io.File;

public class RestoreP extends Preference implements OnPreferenceClickListener {
    public RestoreP(Context context) {
        super(context);
        init();
    }

    public RestoreP(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public RestoreP(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        setOnPreferenceClickListener(this);
    }

    public boolean onPreferenceClick(Preference preference) {
        if (new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup").exists()) {
            new CopyTask(getContext(), false, new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup"), new File(Environment.getDataDirectory(), "data/com.whatsapp")).execute(new File[0]);
        } else {
            Toast.makeText(getContext(),"Can't find a backup in '/sdcard/WhatsApp'!", 0).show();
        }
        return false;
    }
}

