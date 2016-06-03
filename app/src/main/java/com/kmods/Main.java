package com.kmods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main extends AppCompatActivity {

    static int v1 = 1;
    static int v2 = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(this);
        startActivity(new Intent(this, com.whatsapp.Main.class));
    }

    public static String Apply(final String s) {
        return s.replace("com.whatsapp", "com.whatsapp").replace("com.whatsapp.util", "com.whatsapp.util").replace("com.whatsapp.Voip", "com.whatsapp.Voip").replace("com.whatsapp.VideoFrameConverter", "com.whatsapp.VideoFrameConverter").replace("com.whatsapp.util.OpusPlayer", "com.whatsapp.util.OpusPlayer").replace("com.whatsapp.proto", "com.whatsapp.proto").replace("com.whatsapp.util.OpusRecorder", "com.whatsapp.util.OpusRecorder");
    }

    private static void init(Context ctx){
        Utils.ctx = ctx;
        Privacy.ctx = ctx;
        if (Utils.ctx == null || Privacy.ctx == null) {
            Utils.ctx = ctx.getApplicationContext();
            Privacy.ctx = ctx.getApplicationContext();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
