package com.plain.bilibilitools.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.plain.bilibilitools.view.LoadingDialog;

/**
 * Create by Plain on 2019/4/9 10:21 PM
 * Description:
 */
public class BaseFragment extends Fragment {

    protected BaseActivity activity;

    private LoadingDialog mLoadingDialog;

    public View mRootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void startLoading(String m) {
        mLoadingDialog = LoadingDialog.createMyDialog(activity, m);
        mLoadingDialog.show();
    }

    public void stopLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

}
