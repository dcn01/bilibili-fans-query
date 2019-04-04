package com.plain.bilibilitools.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.plain.bilibilitools.R;
import com.plain.bilibilitools.base.BaseActivity;
import com.plain.bilibilitools.bean.ResultBean;
import com.plain.bilibilitools.inter.RetrofitInterface;
import com.plain.bilibilitools.utils.ToastUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    @BindView(R.id.et_user_id)
    EditText mEtUserId;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.cv_result)
    CardView mCvResult;
    @BindView(R.id.tv_result)
    TextView mTvResult;

    private String userId;
    private MyHandler mMyHandler;

    private ResultBean.DataBean mDataBean;

    private int mDestroyTag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setInit();
    }


    @Override
    protected void initData() {
        super.initData();
        mMyHandler = new MyHandler(this);
    }

    @OnClick({R.id.btn_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                goSearch();
                startLoading("正在与B站服务器努力交流中...");
                break;
        }
    }

    private void goSearch() {

        if (mEtUserId.getText().toString().equals("")) {
            ToastUtils.showToast(mContext, "请输入用户ID！", ToastUtils.TYPE_WARNING);
            return;
        } else {
            userId = mEtUserId.getText().toString();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.bilibili.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<ResultBean> call = request.getCall(userId);
        call.enqueue(new Callback<ResultBean>() {
            @Override
            public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
                Message message = Message.obtain();
                assert response.body() != null;
                if (response.body().getCode() == 0) {
                    message.what = 0;
                    message.obj = response.body().getData();
                } else {
                    message.what = -1;
                }
                mMyHandler.sendMessage(message);
            }

            @Override
            public void onFailure(Call<ResultBean> call, Throwable t) {
                stopLoading();
                ToastUtils.showToast(mContext, "请求失败", ToastUtils.TYPE_ERROR);
            }
        });

    }

    private static class MyHandler extends Handler {

        private final WeakReference<MainActivity> mWeakReference;

        MyHandler(MainActivity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mWeakReference.get();
            if (activity.mDestroyTag != -1) {
                activity.stopLoading();
                switch (msg.what) {
                    case 0:
                        activity.mDataBean = (ResultBean.DataBean) msg.obj;
                        activity.mCvResult.setVisibility(View.VISIBLE);
                        String follower = String.valueOf(activity.mDataBean.getFollower());
                        activity.mTvResult.setText(follower);
                        if (follower.equals("0")) {
                            ToastUtils.showToast(activity.mContext, "你还没有粉丝，请不要灰心哦！", ToastUtils.TYPE_SUCCESS);
                        }
                        break;
                    case -1:
                        ToastUtils.showToast(activity.mContext, "请求失败", ToastUtils.TYPE_ERROR);
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDestroyTag = -1;
        mMyHandler.removeCallbacksAndMessages(null);
    }
}
