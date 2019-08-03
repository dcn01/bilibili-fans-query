package com.plain.bilibilitools.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.plain.bilibilitools.R;
import com.plain.bilibilitools.base.BaseActivity;

import butterknife.BindView;

public class ZhiHuNewsActivity extends BaseActivity {

    @BindView(R.id.tl_root)
    Toolbar tlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu_news);
        tlRoot.setTitle("知乎日报");
        tlRoot.setTitleTextColor(getResources().getColor(R.color.white));
        tlRoot.setLogo(R.drawable.ic_zhihu_logo);
        tlRoot.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        tlRoot.setNavigationOnClickListener(new View.OnClickListener() {
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

    }

    @Override
    protected void initView() {
        super.initView();

        getNewsLast();

    }

    private void getNewsLast() {


    }
}
