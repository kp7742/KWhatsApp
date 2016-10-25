package com.whatsapp;

import android.app.Application;

import kmods.Utils;

public class Exo extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
