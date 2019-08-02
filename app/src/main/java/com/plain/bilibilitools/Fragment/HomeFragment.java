package com.plain.bilibilitools.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.plain.bilibilitools.R;
import com.plain.bilibilitools.activity.FansQueryActivity;
import com.plain.bilibilitools.adapter.FeatureListAdapter;
import com.plain.bilibilitools.adapter.PicBannerAdapter;
import com.plain.bilibilitools.base.BaseFragment;
import com.plain.bilibilitools.bean.FeatureListBean;
import com.plain.bilibilitools.bean.PicListBean;
import com.plain.bilibilitools.utils.ToastUtils;
import com.plain.rvbannerlibrary.RvBannerView;
import com.plain.rvbannerlibrary.inter.IRvPageTouchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    private Unbinder unbinder;

    @BindView(R.id.rl_table)
    RecyclerView rlTable;
    @BindView(R.id.rvBanner)
    RvBannerView rvBanner;

    private ArrayList<FeatureListBean> featureListBeans;
    private List<PicListBean> mPicList;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, mRootView);
        setInit();
        return mRootView;
    }

    private void setInit() {
        initData();
        initView();
    }

    private void initData() {
        featureListBeans = new ArrayList<>();
        featureListBeans.add(new FeatureListBean
                (R.drawable.ic_favorite_black_24dp, "粉丝查询", "依托B站API，输入用户ID即可查询粉丝数"));

        mPicList = new ArrayList<>();
        mPicList.add(new PicListBean("https://gitee.com/plain-dev/oss/raw/master/upic_library/AKWElE.jpg", "", "APP"));
        mPicList.add(new PicListBean("https://gitee.com/plain-dev/oss/raw/master/upic_library/RlTA4R.jpg", "", "APP"));
        mPicList.add(new PicListBean("https://gitee.com/plain-dev/oss/raw/master/upic_library/CWV8bi.jpg", "", "APP"));

    }

    private void initView() {

        PicBannerAdapter picBannerAdapter = new PicBannerAdapter(getContext(), mPicList);
        picBannerAdapter.setRvPageTouchListener(new IRvPageTouchListener() {
            @Override
            public void clickItem(int position) {
                ToastUtils.showToast(getContext(), mPicList.get(position).getPicUrl(), ToastUtils.TYPE_INFO);
            }
        });
        rvBanner.setAdapter(picBannerAdapter);

        LinearLayoutManager lm = new LinearLayoutManager(activity);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        FeatureListAdapter adapter = new FeatureListAdapter(activity, featureListBeans);
        adapter.setOnItemClickListener(new FeatureListAdapter.OnItemClickListener() {
            @Override
            public void OnClick(int p) {
                switch (p) {
                    case 0:
                        startActivity(new Intent(activity, FansQueryActivity.class));
                        break;
                }
            }
        });
        rlTable.setLayoutManager(lm);
        rlTable.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
