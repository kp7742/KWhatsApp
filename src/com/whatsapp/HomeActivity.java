package com.whatsapp;

import com.mega.*;

public class HomeActivity {
    void onResume(){
        new Update(App.A(),false).execute((String[]) new String[0]);
    }
}
