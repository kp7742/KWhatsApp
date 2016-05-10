package com.whatsapp;

import com.mega.*;

public class HomeActivity {
    void onResume(){
        new Update2(App.A()).execute((String[]) new String[0]);
        Mega.init(App.A());
    }
}
