package kmods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.Toast;

import com.whatsapp.*;
import static kmods.Utils.getResID;

public class Settings extends mz implements Preference.OnPreferenceClickListener {
    static Context sctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().setSharedPreferencesName("com.whatsapp_preferences");
        this.addPreferencesFromResource(getResID("settings", "xml"));
        this.findPreference("rest").setOnPreferenceClickListener(this);
        this.findPreference("cshort").setOnPreferenceClickListener(this);
    }
    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("rest")) {
            android.os.Process.killProcess(android.os.Process.myPid());
        } else if (preference.getKey().equals("cshort")) {
            Intent sIntent = new Intent(sctx.getApplicationContext(), com.whatsapp.Main.class);
            sIntent.setAction(Intent.ACTION_MAIN);
            sIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, sIntent);
            intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, sctx.getString(getResID("app_name", "string")));
            intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(sctx.getApplicationContext(), getResID("icon", "drawable")));
            intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            try {
                sctx.getApplicationContext().sendBroadcast(intent);
                Toast.makeText(sctx, "Fixed Shortcut Created To Home!", Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                Toast.makeText(sctx, "Error To Create Shortcut!", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(Utils.Auto_restart()) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}