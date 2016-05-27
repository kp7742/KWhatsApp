package com.kmods;

import android.content.Context;
import android.graphics.drawable.Drawable;

import static com.kmods.Utils.getResID;

public class BubbleS {
    private static Context ctx = Utils.ctx;
    public static int BubbleStyle(final int n){
        int i1 = Integer.parseInt(ctx.getSharedPreferences("KMODS", 0).getString("bubble_s", "0"));
        int n1 = 0;int n2 = 0;int n3 = 0;int n4 = 0;
        switch (i1){
            case 0: {
                n1 = getResID("balloon_incoming_normal", "drawable");
                n2 = getResID("balloon_outgoing_normal", "drawable");
                n3 = getResID("balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 1: {
                n1 = getResID("fbm_balloon_incoming_normal", "drawable");
                n2 = getResID("fbm_balloon_outgoing_normal", "drawable");
                n3 = getResID("fbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("fbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 2: {
                n1 = getResID("hangouts_balloon_incoming_normal", "drawable");
                n2 = getResID("hangouts_balloon_outgoing_normal", "drawable");
                n3 = getResID("hangouts_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("hangouts_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 3: {
                n1 = getResID("wapaper_balloon_incoming_normal", "drawable");
                n2 = getResID("wapaper_balloon_outgoing_normal", "drawable");
                n3 = getResID("wapaper_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("wapaper_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 4: {
                n1 = getResID("wp_balloon_incoming_normal", "drawable");
                n2 = getResID("wp_balloon_outgoing_normal", "drawable");
                n3 = getResID("wp_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("wp_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 5: {
                n1 = getResID("walb_balloon_incoming_normal", "drawable");
                n2 = getResID("walb_balloon_outgoing_normal", "drawable");
                n3 = getResID("walb_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("walb_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 6: {
                n1 = getResID("materialized_balloon_incoming_normal", "drawable");
                n2 = getResID("materialized_balloon_outgoing_normal", "drawable");
                n3 = getResID("materialized_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("materialized_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 7: {
                n1 = getResID("telegram_balloon_incoming_normal", "drawable");
                n2 = getResID("telegram_balloon_outgoing_normal", "drawable");
                n3 = getResID("telegram_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("telegram_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 8: {
                n1 = getResID("hike_balloon_incoming_normal", "drawable");
                n2 = getResID("hike_balloon_outgoing_normal", "drawable");
                n3 = getResID("hike_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("hike_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 9: {
                n1 = getResID("bryed_balloon_incoming_normal", "drawable");
                n2 = getResID("bryed_balloon_outgoing_normal", "drawable");
                n3 = getResID("bryed_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("bryed_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 10: {
                n1 = getResID("chaton_balloon_incoming_normal", "drawable");
                n2 = getResID("chaton_balloon_outgoing_normal", "drawable");
                n3 = getResID("chaton_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("chaton_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 11: {
                n1 = getResID("bbm_balloon_incoming_normal", "drawable");
                n2 = getResID("bbm_balloon_outgoing_normal", "drawable");
                n3 = getResID("bbm_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("bbm_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 12: {
                n1 = getResID("doodlehang_balloon_incoming_normal", "drawable");
                n2 = getResID("doodlehang_balloon_outgoing_normal", "drawable");
                n3 = getResID("doodlehang_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("doodlehang_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 13: {
                n1 = getResID("fold_balloon_incoming_normal", "drawable");
                n2 = getResID("fold_balloon_outgoing_normal", "drawable");
                n3 = getResID("fold_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("fold_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 14: {
                n1 = getResID("eclipsis_balloon_incoming_normal", "drawable");
                n2 = getResID("eclipsis_balloon_outgoing_normal", "drawable");
                n3 = getResID("eclipsis_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("eclipsis_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 15: {
                n1 = getResID("altcr_balloon_incoming_normal", "drawable");
                n2 = getResID("altcr_balloon_outgoing_normal", "drawable");
                n3 = getResID("altcr_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("altcr_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 16: {
                n1 = getResID("apple_balloon_incoming_normal", "drawable");
                n2 = getResID("apple_balloon_outgoing_normal", "drawable");
                n3 = getResID("apple_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("apple_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 17: {
                n1 = getResID("gosms_balloon_incoming_normal", "drawable");
                n2 = getResID("gosms_balloon_outgoing_normal", "drawable");
                n3 = getResID("gosms_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("gosms_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 18: {
                n1 = getResID("in_balloon_incoming_normal", "drawable");
                n2 = getResID("in_balloon_outgoing_normal", "drawable");
                n3 = getResID("in_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("in_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 19: {
                n1 = getResID("md_balloon_incoming_normal", "drawable");
                n2 = getResID("md_balloon_outgoing_normal", "drawable");
                n3 = getResID("md_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("md_balloon_incoming_normal_ext", "drawable");
                break;
            }
            case 20: {
                n1 = getResID("trans_balloon_incoming_normal", "drawable");
                n2 = getResID("trans_balloon_outgoing_normal", "drawable");
                n3 = getResID("trans_balloon_outgoing_normal_ext", "drawable");
                n4 = getResID("trans_balloon_incoming_normal_ext", "drawable");
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
    public static Drawable BubbleI(int i){
        return ctx.getResources().getDrawable(BubbleStyle(i));
    }
}
