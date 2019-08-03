package com.plain.bilibilitools.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.plain.bilibilitools.view.LoadingDialog;

/**
 * Create by Plain on 2019/4/4 10:08 PM
 * Description: APP 所有Activity -----> 父Activity
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    private LoadingDialog mLoadingDialog = null;

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

    /**
     * 显示加载框
     *
     * @param m 提示信息
     */
    public void startLoading(String m) {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.createMyDialog(this, m);
        }
        mLoadingDialog.show();
    }

    /**
     * 销毁加载框
     */
    public void stopLoading() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
