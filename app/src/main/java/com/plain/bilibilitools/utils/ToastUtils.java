package com.plain.bilibilitools.utils;

import android.content.Context;

import es.dmoral.toasty.Toasty;

/**
 * Create by Plain on 2019/4/4 10:26 PM
 * Description: 吐司工具类
 */
public class ToastUtils {

    public final static String TYPE_ERROR = "1";
    public final static String TYPE_SUCCESS = "2";
    public final static String TYPE_INFO = "3";
    public final static String TYPE_WARNING = "4";
    public final static String TYPE_NORMAL = "5";

    public static void showToast(Context c, String m, String t) {
        switch (t) {
            case "1":
                Toasty.error(c, m).show();
                break;
            case "2":
                Toasty.success(c, m).show();
                break;
            case "3":
                Toasty.info(c, m).show();
                break;
            case "4":
                Toasty.warning(c, m).show();
                break;
            case "5":
                Toasty.normal(c, m).show();
                break;
            default:
                Toasty.normal(c, m).show();
                break;
        }
    }

}
