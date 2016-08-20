package kmods;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.whatsapp.yp;

import java.lang.reflect.Field;

public class Privacy extends yp {
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
            } else if (o.contains("@s.whatsapp.net")) {
                s = "G";
            } else if (o.contains("g.us")) {
                s = "G";
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
    public static boolean CHidePlay() {
        Boolean b;
        if(getPrivacyB("HidePlay")){
            b = getPrivacyB("HidePlay");
        }  else {
            b = getPrivacyB(JID + "_HidePlay");
        }
        return b;
    }
    public static boolean CHideReceipt() {
        Boolean b;
        if(getPrivacyB("HideReceipt")){
            b = getPrivacyB("HideReceipt");
        } else {
            b = getPrivacyB(JID + "_HideReceipt");
        }
        return b;
    }
    public static boolean CHideCR(final String s) {
        boolean b;
        if (s != null) {
            b = getPrivacyB(JID + "_HideCompose");
        }
        else {
            b = getPrivacyB(JID + "_HideRecord");
        }
        return b;
    }
    //Global Privacy
    public static boolean HideSeen(){
        return getPrivacyB("hide_seen");
    }
    public static boolean HideCR(final String s) {
        boolean b;
        if (s != null) {
            b = getPrivacyB("HideCompose");
        }
        else {
            b = getPrivacyB("HideRecord");
        }
        return b;
    }
}
