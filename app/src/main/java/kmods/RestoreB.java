package kmods;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class RestoreB extends Button implements View.OnClickListener {
    public RestoreB(final Context context) {
        super(context);
        this.init();
    }
    public RestoreB(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    public RestoreB(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    private void init() {
        this.setOnClickListener(this);
    }
    public void onClick(final View view) {
        if (new File(Environment.getExternalStorageDirectory(), "WhatsApp/GKBackup/com.whatsapp").exists()) {
            new CopyTask(getContext(), false, new File(Environment.getExternalStorageDirectory(), "WhatsApp/GKBackup/com.whatsapp"), new File(Environment.getDataDirectory(), "data/com.whatsapp")).execute(new File[0]);
        } else {
            Toast.makeText(getContext(),"Can't find a backup in '/sdcard/WhatsApp/GKBackup/'!", Toast.LENGTH_SHORT).show();
        }
    }
}
