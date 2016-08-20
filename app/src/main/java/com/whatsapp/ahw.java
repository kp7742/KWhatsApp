package com.whatsapp;

import kmods.Utils;

public class ahw {//Toast
   void a(String s){
       if(Utils.contact_online_toast()) {
           App$Me m = new App$Me("s", "s1");
           kmods.plus.Utils.checkContactOnline(App.J(), s, m.jabber_id);
       }
   }
}
