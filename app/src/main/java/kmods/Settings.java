package kmods;

import android.os.Bundle;
import android.preference.Preference;
import com.whatsapp.yp;
import static kmods.Utils.getResID;

public class Settings extends yp implements Preference.OnPreferenceClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().setSharedPreferencesName("com.whatsapp_preferences");
        this.addPreferencesFromResource(getResID("settings", "xml"));
        this.findPreference("rest").setOnPreferenceClickListener(this);
    }
    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals("rest")) {
            android.os.Process.killProcess(android.os.Process.myPid());
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
