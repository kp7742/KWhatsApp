package com.kmods;

import android.content.Context;
import android.support.v7.app.ActionBar;

import java.lang.reflect.Field;

public class Mod {
    public static Context ctx;
    public static int getResId(String s1, String s2){
        return ctx.getResources().getIdentifier(s1, s2, ctx.getPackageName());
    }
    public static void RestartApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    public static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    public static String Apply(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }
    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }
    public static void ShowName(final ActionBar actionBar, final Context context) {
        CharSequence a = "KWhatsApp";
        if (getBoolean("show_my_name_check")) {
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(a);
            }
        }
        actionBar.setTitle(a);
    }
    public static int BubbleStyle(final int n){
        int i1 = Integer.parseInt(ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("bubble_s", "0"));
        int n1 = getResId("balloon_incoming_normal", "drawable");
        int n2 = getResId("balloon_outgoing_normal", "drawable");
        int n3 = getResId("balloon_outgoing_normal_ext", "drawable");
        int n4 = getResId("balloon_incoming_normal_ext", "drawable");
        switch (i1){
            case 0: {
                n1 = getResId("balloon_incoming_normal", "drawable");
                n2 = getResId("balloon_outgoing_normal", "drawable");
                n3 = getResId("balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 1: {
                n1 = getResId("fbm_balloon_incoming_normal", "drawable");
                n2 = getResId("fbm_balloon_outgoing_normal", "drawable");
                n3 = getResId("fbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("fbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 2: {
                n1 = getResId("hangouts_balloon_incoming_normal", "drawable");
                n2 = getResId("hangouts_balloon_outgoing_normal", "drawable");
                n3 = getResId("hangouts_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("hangouts_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 3: {
                n1 = getResId("wapaper_balloon_incoming_normal", "drawable");
                n2 = getResId("wapaper_balloon_outgoing_normal", "drawable");
                n3 = getResId("wapaper_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("wapaper_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 4: {
                n1 = getResId("wp_balloon_incoming_normal", "drawable");
                n2 = getResId("wp_balloon_outgoing_normal", "drawable");
                n3 = getResId("wp_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("wp_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 5: {
                n1 = getResId("walb_balloon_incoming_normal", "drawable");
                n2 = getResId("walb_balloon_outgoing_normal", "drawable");
                n3 = getResId("walb_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("walb_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 6: {
                n1 = getResId("materialized_balloon_incoming_normal", "drawable");
                n2 = getResId("materialized_balloon_outgoing_normal", "drawable");
                n3 = getResId("materialized_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("materialized_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 7: {
                n1 = getResId("telegram_balloon_incoming_normal", "drawable");
                n2 = getResId("telegram_balloon_outgoing_normal", "drawable");
                n3 = getResId("telegram_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("telegram_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 8: {
                n1 = getResId("hike_balloon_incoming_normal", "drawable");
                n2 = getResId("hike_balloon_outgoing_normal", "drawable");
                n3 = getResId("hike_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("hike_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 9: {
                n1 = getResId("bryed_balloon_incoming_normal", "drawable");
                n2 = getResId("bryed_balloon_outgoing_normal", "drawable");
                n3 = getResId("bryed_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("bryed_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 10: {
                n1 = getResId("chaton_balloon_incoming_normal", "drawable");
                n2 = getResId("chaton_balloon_outgoing_normal", "drawable");
                n3 = getResId("chaton_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("chaton_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 11: {
                n1 = getResId("bbm_balloon_incoming_normal", "drawable");
                n2 = getResId("bbm_balloon_outgoing_normal", "drawable");
                n3 = getResId("bbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("bbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 12: {
                n1 = getResId("doodlehang_balloon_incoming_normal", "drawable");
                n2 = getResId("doodlehang_balloon_outgoing_normal", "drawable");
                n3 = getResId("doodlehang_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("doodlehang_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 13: {
                n1 = getResId("fold_balloon_incoming_normal", "drawable");
                n2 = getResId("fold_balloon_outgoing_normal", "drawable");
                n3 = getResId("fold_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("fold_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 14: {
                n1 = getResId("eclipsis_balloon_incoming_normal", "drawable");
                n2 = getResId("eclipsis_balloon_outgoing_normal", "drawable");
                n3 = getResId("eclipsis_balloon_outgoing_normal_ext", "drawable");
                n4 = getResId("eclipsis_balloon_incoming_normal_ext", "drawable");
                break;
            }
        }
        switch (n) {
            default: {
                return n1;
            }
            case 1: {
                return n2;
            }
            case 2: {
                return n3;
            }
            case 3: {
                return n4;
            }
        }
    }
    public static String GetType(Object o) {
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
                catch (Exception ex) {}
            }
        }
        o = "C";
        return (String)o;
    }
    public static int getUpSize() {
        return 5120;
    }

    public static int getMaxImg() {
        return 5315;
    }
    public static boolean TxtSelect() {
        return getBoolean("txt_select");
    }
    public static void init(final Context ctx) {
        Settings.initContextVar(Mod.ctx = ctx);
    }
    public static boolean getHideInfo() {
        return getBoolean("hideinfo");
    }
    public static boolean Img_limit() {
        return getBoolean("img_limit");
    }
    public static boolean HideSeen() {
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
