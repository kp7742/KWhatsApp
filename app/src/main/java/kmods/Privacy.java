package kmods;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.whatsapp.ut;

import java.lang.reflect.Field;

public class Privacy extends ut {
    static Context pctx;
    static String JID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().setSharedPreferencesName("kmods_privacy");
        this.addPreferencesFromResource(Utils.getResID("privacy", "xml"));
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        android.os.Process.killProcess(android.os.Process.myPid());
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
    private static String GetType2(Object o) {
        for (final Field field : o.getClass().getFields()) {
            if (String.class.isAssignableFrom(field.getType())) {
                try {
                    final Object value = field.get(o);
                    if (value != null) {
                        return value.toString();
                    }
                }
                catch (Exception ignored) {}
            }
        }
        return null;
    }
    static boolean getPrivacyB(final String s) {
        return pctx.getSharedPreferences("kmods_privacy", 0).getBoolean(s, false);
    }
    public static void SetJID(String s) {
        JID = s;
        SharedPreferences prefs = pctx.getSharedPreferences("kmods_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        edit.apply();
    }
    //Contact Privacy
    public static boolean AlwaysOnline(){
        return getPrivacyB("always_online");
    }
    public static boolean HideSeen(){
        return getPrivacyB("hide_seen");
    }
    public static boolean HideCR(String JIDs,final int n) {
        if (n == 1) {
            if(getPrivacyB(JIDs)) return getPrivacyB(JIDs + "_HideRecord");
            return getPrivacyB("HideRecord");
        } else {
            if(getPrivacyB(JIDs)) return getPrivacyB(JIDs + "_HideCompose");
            return getPrivacyB("HideCompose");
        }
    }
    public static boolean HideRead(final Object o) {
        String JIDs = GetType2(o);
        if(getPrivacyB(JIDs)) return getPrivacyB(JIDs + "_HideRead");
        return getPrivacyB(GetType(o) + "_HideRead");
    }
    public static boolean HidePlay(final Object o) {
        String JIDs = GetType2(o);
        if(getPrivacyB(JIDs)) return getPrivacyB(JIDs + "_HidePlay");
        return getPrivacyB(GetType(o) + "_HidePlay");
    }
    public static boolean HideReceipt(final Object o) {
        String JIDs = GetType2(o);
        if(getPrivacyB(JIDs)) return getPrivacyB(JIDs + "_HideReceipt");
        return getPrivacyB(GetType(o) + "_HideReceipt");
    }
}