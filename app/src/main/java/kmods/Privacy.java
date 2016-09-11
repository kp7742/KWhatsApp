package kmods;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.whatsapp.ti;

import java.lang.reflect.Field;

import static kmods.Utils.getResID;

public class Privacy extends ti {
    static Context pctx;
    static String JID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().setSharedPreferencesName("geekmods_privacy");
        this.addPreferencesFromResource(getResID("privacy", "xml"));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private static String GetType(Object o) {
        for (final Field field : o.getClass().getFields()) {
            if (String.class.isAssignableFrom(field.getType())) {
                try {
                    final Object value = field.get(o);
                    if (value != null) {
                        if (value.toString().contains("@broadcast")) {
                            o = "B";
                            return (String)o;
                        } else if (value.toString().contains("@s.whatsapp.net")) {
                            o = "G";
                            return (String)o;
                        } else if (value.toString().contains("g.us")) {
                            o = "G";
                            return (String)o;
                        }
                    }
                }
                catch (Exception ignored) {}
            }
        }
        o = "C";
        return (String)o;
    }
    private static String GetType(String o) {
        String s = "C";
        if (o != null) {
            if (o.contains("@broadcast")) {
                s = "B";
                return s;
            } else if (o.contains("@s.whatsapp.net")) {
                s = "G";
                return s;
            } else if (o.contains("g.us")) {
                s = "G";
                return s;
            }
        }
        return s;
    }
    static boolean getPrivacyB(final String s) {
        return pctx.getSharedPreferences("geekmods_privacy", 0).getBoolean(s, false);
    }
    public static void SetJID(String s) {
        JID = s;
        SharedPreferences prefs = pctx.getSharedPreferences("geekmods_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        if(!prefs.contains(JID + "_HideRead") || !prefs.contains(JID + "_HidePlay") || !prefs.contains(JID + "_HideRecord") || !prefs.contains(JID + "_HideCompose") || !prefs.contains(JID + "_HideReceipt")) {
            edit.putBoolean(JID + "_HideRead", false);
            edit.putBoolean(JID + "_HideReceipt", false);
            edit.putBoolean(JID + "_HideCompose", false);
            edit.putBoolean(JID + "_HideRecord", false);
            edit.putBoolean(JID + "_HidePlay", false);
            edit.apply();
        }
    }
    //Contact Privacy
    public static boolean AlwaysOnline(){
        return getPrivacyB("always_online");
    }
    public static boolean HideSeen(){
        return getPrivacyB("hide_seen");
    }
    public static boolean HideCR(String JIDs,final int n) {
        boolean b;
        if (n == 1) {
            b = getPrivacyB("HideRecord") || getPrivacyB(JIDs + "_HideRecord");
        } else {
            b = getPrivacyB("HideCompose") || getPrivacyB(JIDs + "_HideCompose");
        }
        return b;
    }
    public static boolean HideRead(final com.whatsapp.alh o) {
        String JIDs = o.a.a;
        return getPrivacyB(GetType(o) + "_HideRead") || getPrivacyB(JIDs + "_HideRead");
    }
    public static boolean HidePlay(final com.whatsapp.protocol.by o) {
        String JIDs = o.e.a;
        return getPrivacyB(GetType(o) + "_HidePlay") || getPrivacyB(JIDs + "_HidePlay");
    }
    public static boolean HideReceipt(final com.whatsapp.protocol.by o) {
        String JIDs = o.e.a;
        return getPrivacyB(GetType(o) + "_HideReceipt") || getPrivacyB(JIDs + "_HideReceipt");
    }
}