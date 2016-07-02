package com.whatsapp;

import android.support.v7.app.AppCompatActivity;
import com.kmods.Update2;
import com.kmods.Utils;

public class HomeActivity extends AppCompatActivity {
    @Override
    public void onResume() {
        super.onResume();
        if(Utils.Auto_update()) {
            new Update2(this).execute((String[]) new String[0]);
        }
        Utils.ShowName(getSupportActionBar(), this);
    }
}
