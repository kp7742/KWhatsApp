package kmods;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.whatsapp.yr;

import java.lang.reflect.Field;

public class Privacy extends yr {
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
        return pctx.getSharedPreferences("kmods_privacy", 0).getBoolean(s, false);
    }
    public static void SetJID(String s) {
        JID = s;
        SharedPreferences prefs = pctx.getSharedPreferences("kmods_privacy", 0);
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
    public static boolean CHideRead() {
        Boolean b;
        if(getPrivacyB("HideRead")){
            b = getPrivacyB("HideRead");
        } else {
            b = getPrivacyB(JID + "_HideRead");
        }
        return b;
    }
    public static boolean AlwaysOnline(){
        return getPrivacyB("always_online");
    }
    public static boolean HideSeen(){
        return getPrivacyB("hide_seen");
    }
    public static boolean HideCR(final int n) {
        boolean b;
        if (n == 1) {
            if(getPrivacyB("HideRecord")) {
                b = getPrivacyB("HideRecord");
            } else {
                b = getPrivacyB(JID + "_HideRecord");
            }
        } else {
            if(getPrivacyB("HideCompose")) {
                b = getPrivacyB("HideCompose");
            } else {
                b = getPrivacyB(JID + "_HideCompose");
            }
        }
        return b;
    }
    public static boolean HideRead(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) +"_HideRead")){
            b = getPrivacyB(GetType(o) +"_HideRead");
        } else {
            b = getPrivacyB(JID + "_HideRead");
        }
        return b;
    }
    public static boolean HidePlay(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) + "_HidePlay")){
            b = getPrivacyB(GetType(o) + "_HidePlay");
        }  else {
            b = getPrivacyB(JID + "_HidePlay");
        }
        return b;
    }
    public static boolean HideReceipt(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) + "_HideReceipt")){
            b = getPrivacyB(GetType(o) + "_HideReceipt");
        } else {
            b = getPrivacyB(JID + "_HideReceipt");
        }
        return b;
    }
}
