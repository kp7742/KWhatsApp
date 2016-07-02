package com.kmods;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.whatsapp.Conversation;
import com.whatsapp.abg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.kmods.Privacy.JID;

public class Utils {
    static int v1 = 1;
    static int v2 = 9;
    public static int MaxImg = 5315;
    public static int UpSize = 5120;
    private static Context ctx;
    static boolean getBoolean(final String s) {
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
        if(Multi_chats() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
            intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        return intent;
    }
    public static void ShowName(final ActionBar actionBar, final Context ctx) {
        CharSequence a = "WhatsApp";
        if (getBoolean("show_my_name_check")) {
            a = ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("push_name", "");
            if (getBoolean("show_my_status_check")) {
                actionBar.setSubtitle(ctx.getSharedPreferences("com.whatsapp_preferences", 0).getString("my_current_status", ""));
            }
        }
        actionBar.setTitle(a);
    }
    static int getResID(String name, String type){
        return ctx.getResources().getIdentifier(name, type, ctx.getPackageName());
    }
    private static boolean Multi_chats() {
        return getBoolean("Multi_chats");
    }
    static void SetPrefString(final String name, final String value) {
        final SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(ctx).edit();
        edit.putString(name, value);
        edit.apply();
    }
    public static void init(Context ctx){
        if (ctx instanceof Activity) {
            Utils.ctx = ctx.getApplicationContext();
            Privacy.pctx = ctx.getApplicationContext();
        } else {
            Utils.ctx = ctx;
            Privacy.pctx = ctx;
        }
        if(Utils.ctx == null){
            Log.d("KMods", "Context var initialized to NULL!!!");
        }
    }
    public static String ChangeP(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp")
                .replace("com.whatsapp.util", "com.whatsapp.util")
                .replace("com.whatsapp.Voip", "com.whatsapp.Voip")
                .replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter")
                .replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer")
                .replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder")
                .replace("com.whatsapp.proto", "com.whatsapp.proto");
    }
    public static String ChangeF(final String s) {
        String replace = s;
        String WA = "WhatsApp";
        if (!WA.equals("KWhatsApp")) {
            return replace;
        }
        try {
            final String lowerCase = s.toLowerCase();
            if (!lowerCase.contains("whatsapp")) {
                replace = s;
                if (!lowerCase.contains("WhatsApp")) {
                    return replace;
                }
            }
            replace = s;
            if (!lowerCase.contains("k")) {
                replace = s;
                if (!lowerCase.contains(".jpg")) {
                    replace = s;
                    if (!lowerCase.contains(".3gp")) {
                        replace = s;
                        if (!lowerCase.contains(".mp4")) {
                            replace = s;
                            if (!lowerCase.contains(".mp3")) {
                                replace = s;
                                if (!lowerCase.contains(".3ga")) {
                                    replace = s;
                                    if (!lowerCase.contains(".aac")) {
                                        replace = s;
                                        if (!lowerCase.contains(".m4a")) {
                                            replace = s.replace("whatsapp", "kwhatsapp").replace("WhatsApp", "KWhatsapp");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return replace;
        }
        catch (Exception ex) {
            replace = s;
            return replace;
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
    public static boolean Auto_update() {
        return getBoolean("Auto_update");
    }
    static boolean Auto_restart() {
        return getBoolean("Auto_restart");
    }
    public static boolean Img_limit() {
        return getBoolean("img_limit");
    }
    public static boolean CallBHide() {
        return getBoolean("call_btn");
    }
    public static void CreateShortcut(Context ctx){
        Intent sIntent = new Intent(ctx,  com.kmods.Main.class);
        sIntent.setAction(Intent.ACTION_MAIN);
        sIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent intent = new Intent();
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, sIntent);
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "KWhatsApp");
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(ctx, getResID("icon", "drawable")));
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        ctx.sendBroadcast(intent);
    }
    //custom things
    public static boolean TxtSelect() {
        Boolean b;
        if(getBoolean("txt_select")){
            b = getBoolean("txt_select");
        } else {
            b = getBoolean(JID + "-txt_select");
        }
        return b;
    }
    public static boolean getHideInfo() {
        Boolean b;
        if(getBoolean("hideinfo")){
            b = getBoolean("hideinfo");
        } else {
            b = getBoolean(JID + "-hideinfo");
        }
        return b;
    }
}
