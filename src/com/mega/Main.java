package com.mega;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Main extends Activity {
    static int v1 = 1;
    static int v2 = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getApplication());
        startActivity(new Intent(this, com.whatsapp.Main.class));
    }
    private static void init(Context ctx){
        Mega.ctx = ctx;
        Settings.ctx = ctx;
        if (Settings.ctx == null || Mega.ctx == null) {
            Mega.ctx = ctx.getApplicationContext();
            Settings.ctx = ctx.getApplicationContext();
        }
    }
    public static String Apply(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }
}
