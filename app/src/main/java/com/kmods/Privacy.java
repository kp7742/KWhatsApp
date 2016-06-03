package com.kmods;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;

public class Privacy {
    public static Context ctx;
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
    public static void SetJID(String s) {
        JID = s;
        SharedPreferences prefs = ctx.getSharedPreferences("kmods_privacy", 0);
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
