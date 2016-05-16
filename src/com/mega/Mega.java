package com.mega;

import android.content.Context;
import android.support.v7.app.ActionBar;
import com.whatsapp.App;

public class Mega {
    static int v1 = 1;
    static int v2 = 3;
    static Context ctx;
    static void RestartApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    public static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("com.whatsapp_preferences", 0).getBoolean(s, false);
    }
    public static String Apply(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }
    public static void init(Context ctx){
        Mega.ctx = ctx;
        Settings.ctx = ctx;
        if (Settings.ctx == null || Mega.ctx == null) {
            Mega.ctx = ctx.getApplicationContext();
            Settings.ctx = ctx.getApplicationContext();
        }
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
    public static boolean Auto_update() {
        return getBoolean("Auto_update");
    }
    public static void ShowName(final ActionBar actionBar, final Context context) {
        CharSequence a = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            a = App.j(context);
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(App.at());
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