package com.whatsapp;

import com.mega.*;

import static com.mega.Mega.init;

public class HomeActivity {
    void onResume(){
        init(App.aO());
        if(Mega.Auto_update()) {
            new Update2(App.aO()).execute((String[]) new String[0]);
        }
    }
}
