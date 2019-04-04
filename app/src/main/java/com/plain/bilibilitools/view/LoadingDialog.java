package com.plain.bilibilitools.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Create by Plain on 2019/4/4 10:20 PM
 * Description: 加载框
 */
public class LoadingDialog extends ProgressDialog {

    private static LoadingDialog myDialog = null;
    private String m;

    private LoadingDialog(Context context, String m) {
        super(context);
        this.m = m;
    }

    private LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("加载中...");
        setMessage(m);
    }

    public static LoadingDialog createMyDialog(Context c, String m) {
        if (myDialog == null) {
            synchronized (LoadingDialog.class) {
                if (myDialog == null) {
                    myDialog = new LoadingDialog(c, m);
                }
            }
        }
        return myDialog;
    }

}
