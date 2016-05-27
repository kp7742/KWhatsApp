package com.kmods;

import android.content.Context;
import android.graphics.drawable.Drawable;

import static com.kmods.Utils.getResID;

public class TickS {
    private static Context ctx = Utils.ctx;
    public static int TickStyle(final int n){
        int i1 = Integer.parseInt(ctx.getSharedPreferences("KMODS", 0).getString("tick_s", "0"));
        int n1 = 0;int n2 = 0;int n3 = 0;int n4 = 0;
        switch (i1){
            case 0: {
                n1 = getResID("message_unsent", "drawable");
                n2 = getResID("message_got_receipt_from_server", "drawable");
                n3 = getResID("message_got_receipt_from_target", "drawable");
                n4 = getResID("message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 1: {
                n1 = getResID("letter_message_unsent", "drawable");
                n2 = getResID("letter_message_got_receipt_from_server", "drawable");
                n3 = getResID("letter_message_got_receipt_from_target", "drawable");
                n4 = getResID("letter_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 2: {
                n1 = getResID("bbm_message_unsent", "drawable");
                n2 = getResID("bbm_message_got_receipt_from_server", "drawable");
                n3 = getResID("bbm_message_got_receipt_from_target", "drawable");
                n4 = getResID("bbm_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 3: {
                n1 = getResID("bpg_message_unsent", "drawable");
                n2 = getResID("bpg_message_got_receipt_from_server", "drawable");
                n3 = getResID("bpg_message_got_receipt_from_target", "drawable");
                n4 = getResID("bpg_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 4: {
                n1 = getResID("circle_message_unsent", "drawable");
                n2 = getResID("circle_message_got_receipt_from_server", "drawable");
                n3 = getResID("circle_message_got_receipt_from_target", "drawable");
                n4 = getResID("circle_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 5: {
                n1 = getResID("heart_message_unsent", "drawable");
                n2 = getResID("heart_message_got_receipt_from_server", "drawable");
                n3 = getResID("heart_message_got_receipt_from_target", "drawable");
                n4 = getResID("heart_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 6: {
                n1 = getResID("hike_message_unsent", "drawable");
                n2 = getResID("hike_message_got_receipt_from_server", "drawable");
                n3 = getResID("hike_message_got_receipt_from_target", "drawable");
                n4 = getResID("hike_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 7: {
                n1 = getResID("messenger_message_unsent", "drawable");
                n2 = getResID("messenger_message_got_receipt_from_server", "drawable");
                n3 = getResID("messenger_message_got_receipt_from_target", "drawable");
                n4 = getResID("messenger_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 8: {
                n1 = getResID("newwaca_message_unsent", "drawable");
                n2 = getResID("newwaca_message_got_receipt_from_server", "drawable");
                n3 = getResID("newwaca_message_got_receipt_from_target", "drawable");
                n4 = getResID("newwaca_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 9: {
                n1 = getResID("oldwaca_message_unsent", "drawable");
                n2 = getResID("oldwaca_message_got_receipt_from_server", "drawable");
                n3 = getResID("oldwaca_message_got_receipt_from_target", "drawable");
                n4 = getResID("oldwaca_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 10: {
                n1 = getResID("ommaterial_message_unsent", "drawable");
                n2 = getResID("ommaterial_message_got_receipt_from_server", "drawable");
                n3 = getResID("ommaterial_message_got_receipt_from_target", "drawable");
                n4 = getResID("ommaterial_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 11: {
                n1 = getResID("quinn_message_unsent", "drawable");
                n2 = getResID("quinn_message_got_receipt_from_server", "drawable");
                n3 = getResID("quinn_message_got_receipt_from_target", "drawable");
                n4 = getResID("quinn_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 12: {
                n1 = getResID("traffic_message_unsent", "drawable");
                n2 = getResID("traffic_message_got_receipt_from_server", "drawable");
                n3 = getResID("traffic_message_got_receipt_from_target", "drawable");
                n4 = getResID("traffic_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 13: {
                n1 = getResID("treble_message_unsent", "drawable");
                n2 = getResID("treble_message_got_receipt_from_server", "drawable");
                n3 = getResID("treble_message_got_receipt_from_target", "drawable");
                n4 = getResID("treble_message_got_read_receipt_from_target", "drawable");
                break;
            }
            case 14: {
                n1 = getResID("x_message_unsent", "drawable");
                n2 = getResID("x_message_got_receipt_from_server", "drawable");
                n3 = getResID("x_message_got_receipt_from_target", "drawable");
                n4 = getResID("x_message_got_read_receipt_from_target", "drawable");
                break;
            }
        }
        switch (n) {
            default: {
                return n1;
            }
            case 1: {
                return n2;
            }
            case 2: {
                return n3;
            }
            case 3: {
                return n4;
            }
        }
    }
    public static Drawable TickI(int i){
        return ctx.getResources().getDrawable(TickStyle(i));
    }
}
