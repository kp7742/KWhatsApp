package com.kmods;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        Fabric.with(this, new Answers(), new Crashlytics());
        Utils.init(this.getApplicationContext());
        this.startActivity(new Intent(this, com.whatsapp.Main.class));
    }
    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}
