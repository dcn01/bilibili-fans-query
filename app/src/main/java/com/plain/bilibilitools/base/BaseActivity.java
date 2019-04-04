package com.plain.bilibilitools.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.plain.bilibilitools.view.LoadingDialog;

/**
 * Create by Plain on 2019/4/4 10:08 PM
 * Description: APP 所有Activity -----> 父Activity
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setInit();
    }

    protected void setInit() {
        initView();
        initData();
    }

    protected void initData() {

    }

    protected void initView() {
    
    }

    public void startLoading(String m) {
        mLoadingDialog = LoadingDialog.createMyDialog(this, m);
        mLoadingDialog.show();
    }

    public void stopLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

}
