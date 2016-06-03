package com.kmods;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import com.whatsapp.App;
import com.whatsapp.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Utils {
    public static int MaxImg = 5315;
    public static int UpSize = 5120;
    public static Context ctx;
    private static boolean getBoolean(final String s) {
        return ctx.getSharedPreferences("KMODS", 0).getBoolean(s, false);
    }
    static void toTxt(String str, String name){
        try {
            FileWriter out = new FileWriter(new File(Environment.getExternalStorageDirectory().getPath(), name));
            out.write(str);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Intent multiTask(Intent intent){
        if(Multi_chats()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }
    public static void ShowName(final ActionBar actionBar, final Context context) {
        CharSequence a = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            a = App.D(context);
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(App.ag());
            }
        }
        actionBar.setTitle(a);
    }
    public static int getResID(String name, String type){
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }
    static void restart(Context ctx) {
        Intent mStartActivity = new Intent(ctx, Main.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(ctx, mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
    private static boolean Multi_chats() {
        return getBoolean("Multi_chats");
    }
    public static void SetPrefString(final String name, final String value) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        edit.putString(name, value);
        edit.apply();
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
