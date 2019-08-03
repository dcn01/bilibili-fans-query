package com.plain.bilibilitools.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.plain.bilibilitools.R;
import com.plain.bilibilitools.base.BaseActivity;
import com.plain.bilibilitools.bean.ResultBean;
import com.plain.bilibilitools.inter.RetrofitInterface;
import com.plain.bilibilitools.utils.BaseUIHandler;
import com.plain.bilibilitools.utils.Constant;
import com.plain.bilibilitools.utils.InputMethodUtils;
import com.plain.bilibilitools.utils.TextUtils;
import com.plain.bilibilitools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.*;

public class FansQueryActivity extends BaseActivity {

    private static final String TAG = "FansQueryActivity";
    @BindView(R.id.et_user_id)
    EditText mEtUserId;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.cv_result)
    CardView mCvResult;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.tl_root)
    Toolbar tlRoot;

    private String userId;
    private MyHandler mMyHandler;
    private ResultBean.DataBean mDataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fans_query);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String shareAction = intent.getAction();
        String shareType = intent.getType();

        if (Intent.ACTION_SEND.equals(shareAction) || shareType != null) {
            if ("text/plain".equals(shareType)) {
                getShareContent(intent);
            }
        } else {
            mEtUserId.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodUtils.getInstance().showSoftInputMethod(mContext, mEtUserId);
                }
            }, 500);
        }

        tlRoot.setTitle("　粉丝查询");
        tlRoot.setTitleTextColor(getResources().getColor(R.color.white));
        tlRoot.setLogo(R.drawable.ic_bilibili_logo);
        tlRoot.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tlRoot.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        setInit();
    }

    @Override
    protected void initData() {
        super.initData();
        mMyHandler = new MyHandler(FansQueryActivity.this);
    }

    /**
     * 处理外部分享接收到的数据
     *
     * @param intent intent
     */
    private void getShareContent(Intent intent) {
        String shareStr = intent.getStringExtra(Intent.EXTRA_TEXT);
        userId = TextUtils.parsingShareUrlGetUserId(shareStr);
        if (!android.text.TextUtils.isEmpty(userId)) {
            mEtUserId.setText(userId);
            goSearch();
        } else {
            ToastUtils.showToast(getApplication(), "地址解析失败", ToastUtils.TYPE_ERROR);
        }

    }

    private void goSearch() {
        if (mEtUserId.getText().toString().equals("")) {
            ToastUtils.showToast(mContext, "请输入用户ID！", ToastUtils.TYPE_WARNING);
            return;
        } else {
            userId = mEtUserId.getText().toString();
        }

        InputMethodUtils.getInstance().hideSoftInputMethod(mEtUserId);
        startLoading("正在与B站服务器努力交流中...");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BILIBILI_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RetrofitInterface request = retrofit.create(RetrofitInterface.class);
        Call<ResultBean> call = request.getFansByUserId(userId);
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

    @OnClick({R.id.btn_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                goSearch();
                break;
        }
    }

    private static class MyHandler extends BaseUIHandler<FansQueryActivity> {

        MyHandler(FansQueryActivity cls) {
            super(cls);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FansQueryActivity a = getRef();
            if (a != null && !a.isFinishing()) {
                a.stopLoading();
                switch (msg.what) {
                    case 0:
                        a.mDataBean = (ResultBean.DataBean) msg.obj;
                        a.mCvResult.setVisibility(VISIBLE);
                        String follower = String.valueOf(a.mDataBean.getFollower());
                        a.mTvResult.setText(follower);
                        if (follower.equals("0")) {
                            ToastUtils.showToast(a.mContext, "你还没有粉丝，请不要灰心哦！", ToastUtils.TYPE_SUCCESS);
                        }
                        break;
                    case -1:
                        ToastUtils.showToast(a.mContext, "请求失败", ToastUtils.TYPE_ERROR);
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMyHandler != null) {
            mMyHandler.removeCallbacksAndMessages(null);
        }
    }
}
