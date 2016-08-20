package com.whatsapp;

import android.support.v7.app.s;
import kmods.Update2;
import kmods.Utils;

public class HomeActivity extends s {
    @Override
    public void onResume() {
        super.onResume();
        if(Utils.Auto_update()) {
            new Update2(this).execute((String[]) new String[0]);
        }
        Utils.ShowName(h(), this);
    }
}
