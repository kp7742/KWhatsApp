package com.kmods;

import android.content.SharedPreferences;

import java.lang.reflect.Field;

import static com.kmods.Utils.ctx;

public class Privacy {
    public static String JID;
    private static String GetType(Object o) {
        String s = null;
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
    public static boolean getPrivacyB(final String s) {
        return ctx.getSharedPreferences("kmods_privacy", 0).getBoolean(s, false);
    }
    //Contact Privacy
    public static void SetPrivacy(String s) {
        JID = s;
        SharedPreferences prefs = ctx.getSharedPreferences("kmods_privacy", 0);
        SharedPreferences.Editor edit = prefs.edit();
        if(!prefs.contains(s + "_HideRead") || !prefs.contains(s + "_HidePlay") || !prefs.contains(s + "_HideRecord") || !prefs.contains(s + "_HidePlay")) {
            edit.putBoolean(s + "_HideRead", false);
            edit.putBoolean(s + "_HidePlay", false);
            edit.putBoolean(s + "_HideRecord", false);
            edit.putBoolean(s + "_HideCompose", false);
            edit.apply();
        }
    }
    public static boolean CHideRead(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) + "_HideRead")){
            b = getPrivacyB(GetType(o) + "_HideRead");
        } else {
            b = getPrivacyB(JID + "_HideRead");
        }
        return b;
    }
    public static boolean CHidePlay(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) + "_HidePlay")){
            b = getPrivacyB(GetType(o) + "_HidePlay");
        }  else {
            b = getPrivacyB(JID + "_HidePlay");
        }
        return b;
    }
    public static boolean CHideReceipt(final Object o) {
        Boolean b;
        if(getPrivacyB(GetType(o) + "_HideReceipt")){
            b = getPrivacyB(GetType(o) + "_HideReceipt");
        } else {
            b = getPrivacyB(JID + "_HideReceipt");
        }
        return b;
    }
    public static boolean CHideCR(final String s) {
        boolean b;
        if (s != null) {
            b = getPrivacyB(JID + "_HideRecord");
        }
        else {
            b = getPrivacyB(JID + "_HideCompose");
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
            b = getPrivacyB("HideRecord");
        }
        else {
            b = getPrivacyB("HideCompose");
        }
        return b;
    }
}
