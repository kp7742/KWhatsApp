package com.mega;

import android.content.Context;
import android.os.Environment;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.AttributeSet;
import android.widget.Toast;

import java.io.File;

public class BackupP extends Preference implements OnPreferenceClickListener {
    public BackupP(Context context) {
        super(context);
        init();
    }

    public BackupP(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public BackupP(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void init() {
        setOnPreferenceClickListener(this);
    }

    public boolean onPreferenceClick(Preference preference) {
        if (new File(Environment.getDataDirectory(), "data/com.whatsapp").exists()) {
            new CopyTask(getContext(), true, new File(Environment.getDataDirectory(), "data/com.whatsapp"), new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup")).execute(new File[0]);
        } else {
            Toast.makeText(getContext(), "Can't find a Data!", 0).show();
        }
            return false;
    }
}

