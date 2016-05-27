package com.kmods;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.whatsapp.HomeActivity;

public class Main extends AppCompatActivity {

    static int v1 = 1;
    static int v2 = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(this);
        startActivity(new Intent(this, HomeActivity.class));
    }

    private static void init(Context ctx){
        Utils.ctx = ctx;
        if (Utils.ctx == null) {
            Utils.ctx = ctx.getApplicationContext();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
