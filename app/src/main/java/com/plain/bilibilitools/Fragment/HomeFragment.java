package com.plain.bilibilitools.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plain.bilibilitools.R;
import com.plain.bilibilitools.activity.FansQueryActivity;
import com.plain.bilibilitools.adapter.FeatureListAdapter;
import com.plain.bilibilitools.base.BaseFragment;
import com.plain.bilibilitools.bean.FeatureListBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {

    private Unbinder unbinder;

    @BindView(R.id.rl_table)
    RecyclerView rlTable;

    private View rootView;
    private ArrayList<FeatureListBean> featureListBeans;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        setInit();
        return rootView;
    }

    private void setInit() {
        initData();
        initView();
    }

    private void initData() {
        featureListBeans = new ArrayList<>();
        featureListBeans.add(new FeatureListBean
                (R.drawable.ic_favorite_black_24dp, "粉丝查询", "依托B站API，输入用户ID即可查询粉丝数"));
    }

    private void initView() {
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
