package com.whatsapp;

import kmods.Utils;

public class ahw {//Toast
   void a(String s){
       if(Utils.contact_online_toast()) {
           kmods.plus.Utils.checkContactOnline(App.u(), s, App.S.jabber_id);
       }
   }
}
