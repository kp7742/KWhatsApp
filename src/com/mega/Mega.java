package com.mega;

import android.content.Context;
import android.support.v7.app.ActionBar;
import com.whatsapp.App;

public class Mega {
    static Context ctx;
    static void RestartApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    private static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    public static boolean Archv_chats() {
        return getBoolean("Archv_chats");
    }
    public static boolean Audio_ears() {
        return getBoolean("Audio_ears");
    }
    public static boolean Audio_sensor() {
        return getBoolean("Audio_sensor");
    }
    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }
    public static void ShowName(final ActionBar actionBar, final Context context) {
        CharSequence a = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            a = App.A(context);
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(App.aR());
            }
        }
        actionBar.setTitle(a);
    }
    public static int getResId(String s1, String s2){
        return ctx.getResources().getIdentifier(s1, s2, ctx.getPackageName());
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
    public static boolean getHideInfo() {
        return getBoolean("hideinfo");
    }
    public static boolean Img_limit() {
        return getBoolean("img_limit");
    }
}