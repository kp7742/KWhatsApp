package com.whatsapp;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    static Context ctx;
    public static App.Me M;
    public static Context z() {
        return ctx;
    }
    public class Me {
        String jabber_id;
    }
}
