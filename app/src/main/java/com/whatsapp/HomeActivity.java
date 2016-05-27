package com.whatsapp;

import android.support.v7.app.AppCompatActivity;
import com.kmods.Update2;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        new Update2(this).execute((String[]) new String[0]);
    }
}
