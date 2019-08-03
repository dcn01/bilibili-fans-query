package com.plain.bilibilitools.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Author : Plain
 * ClassName : com.plain.bilibilitools.utils
 * Description : 输入法工具类
 * CreateDate : 2019-08-03 11:25
 * Version : V1.0
 */
public class InputMethodUtils {

    private volatile static InputMethodUtils sInstance = null;
    private InputMethodManager mImm = null;

    private InputMethodUtils() {
    }

    public static InputMethodUtils getInstance() {

        if (sInstance == null) {
            synchronized (InputMethodUtils.class) {
                if (sInstance == null) {
                    sInstance = new InputMethodUtils();
                }
            }
        }
        return sInstance;
    }

    public void showSoftInputMethod(@NonNull Context context, @NonNull EditText editText) {
        if (mImm == null) {
            mImm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        }

        editText.requestFocus();
        mImm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

    }

    public void hideSoftInputMethod(@NonNull EditText editText) {
        if (mImm != null) {
            mImm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            mImm = null;
        }
    }

}
