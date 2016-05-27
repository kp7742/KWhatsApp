package com.kmods;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.preference.Preference;
import android.util.AttributeSet;
import android.widget.Toast;

import java.io.File;

public class ActionP extends Preference {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ActionP(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ActionP(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ActionP(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActionP(Context context) {
        super(context);
    }

    @Override
    protected void onClick() {
        super.onClick();
        switch (getKey()) {
            case "cfu":
                new Update(getContext()).execute((String[]) new String[0]);
                break;
            case "backup":
                if (new File(Environment.getDataDirectory(), "data/com.whatsapp").exists()) {
                    new CopyTask(getContext(), true, new File(Environment.getDataDirectory(), "data/com.whatsapp"), new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup")).execute(new File[0]);
                } else {
                    Toast.makeText(getContext(), "Can't find a Data!", Toast.LENGTH_SHORT).show();
                }
                break;
            case "restore":
                if (new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup").exists()) {
                    new CopyTask(getContext(), false, new File(Environment.getExternalStorageDirectory(), "WhatsApp/KBackup"), new File(Environment.getDataDirectory(), "data/com.whatsapp")).execute(new File[0]);
                } else {
                    Toast.makeText(getContext(),"Can't find a backup in '/sdcard/WhatsApp'!", Toast.LENGTH_SHORT).show();
                }
                break;
            case "rest":
                Utils.restart(getContext());
                break;
            case "privacy":
                getContext().startActivity(new Intent(getContext(), com.kmods.Activity.Privacy.class));
                break;
            case "bat":
                getContext().startActivity(new Intent(getContext(), com.kmods.Activity.Bubble.class));
                break;
        }
    }
}
