package com.plain.bilibilitools.utils;

import android.os.Handler;

import java.lang.ref.WeakReference;

/**
 * Author : Plain
 * ClassName : com.plain.bilibilitools.utils
 * Description : UIHandler（防止内存泄漏Handler）
 * CreateDate : 2019-08-03 10:32
 * Version : V1.0
 */
public class BaseUIHandler<T> extends Handler {

    private final WeakReference<T> mTWeakReference;

    protected BaseUIHandler(T cls) {
        mTWeakReference = new WeakReference<>(cls);
    }

    protected T getRef(){
        return mTWeakReference.get();
    }

}
