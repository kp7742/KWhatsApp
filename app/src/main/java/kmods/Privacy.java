package kmods;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.whatsapp.*;

import java.lang.reflect.Field;

import static kmods.Utils.getResID;

public class Privacy extends ui {
    static Context pctx;
    static String JID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getPreferenceManager().setSharedPreferencesName("kmods_privacy");
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
            if(!getPrivacyB(JIDs)) {
                return getPrivacyB("HideRecord");
            } else {
                return getPrivacyB(JIDs + "_HideRecord");
            }
        } else {
            if(!getPrivacyB(JIDs)) {
                return getPrivacyB("HideCompose");
            } else {
                return getPrivacyB(JIDs + "_HideCompose");
            }
        }
    }
    public static boolean HideRead(final com.whatsapp.anh o) {
        String JIDs = o.a.a;
        if(!getPrivacyB(JIDs)) {
            return getPrivacyB(GetType(o) + "_HideRead");
        } else {
            return getPrivacyB(JIDs + "_HideRead");
        }
    }
    public static boolean HidePlay(final com.whatsapp.protocol.bx o) {
        String JIDs = o.e.a;
        if(!getPrivacyB(JIDs)) {
            return getPrivacyB(GetType(o) + "_HidePlay");
        } else {
            return getPrivacyB(JIDs + "_HidePlay");
        }
    }
    public static boolean HideReceipt(final com.whatsapp.protocol.bx o) {
        String JIDs = o.e.a;
        if(!getPrivacyB(JIDs)) {
            return getPrivacyB(GetType(o) + "_HideReceipt");
        } else {
            return getPrivacyB(JIDs + "_HideReceipt");
        }
    }
}