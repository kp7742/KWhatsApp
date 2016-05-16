package com.mega;

import java.lang.reflect.Field;

import static com.mega.Mega.getBoolean;

public class Privacy {
    private static String GetType(Object o) {
        for (final Field field : o.getClass().getFields()) {
            if (String.class.isAssignableFrom(field.getType())) {
                try {
                    final Object value = field.get(o);
                    if (value != null) {
                        if (value.toString().contains("@broadcast")) {
                            o = "B";
                            return (String)o;
                        }
                        if (value.toString().contains("@s.whatsapp.net")) {
                            o = "G";
                            return (String)o;
                        }
                        if (value.toString().contains("g.us")) {
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
    public static boolean HideSeen(){
        return getBoolean("hide_seen");
    }
    public static boolean HideRead(final Object o) {
        return getBoolean("HideRead" + GetType(o));
    }
    public static boolean HideCR(final String s) {
        boolean b;
        if (s != null) {
            b = getBoolean("HideRecord");
        }
        else {
            b = getBoolean("HideCompose");
        }
        return b;
    }
    public static boolean HidePlay(final Object o) {
        return getBoolean("HidePlay" + GetType(o));
    }
    public static boolean HideReceipt(final Object o) {
        return getBoolean("HideReceipt" + GetType(o));
    }
}
