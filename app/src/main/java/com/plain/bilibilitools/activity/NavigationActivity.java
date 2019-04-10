package com.plain.bilibilitools.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.plain.bilibilitools.Fragment.FindFragment;
import com.plain.bilibilitools.Fragment.HomeFragment;
import com.plain.bilibilitools.Fragment.MyFragment;
import com.plain.bilibilitools.R;
import com.plain.bilibilitools.base.BaseActivity;
import com.plain.bilibilitools.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NavigationActivity extends BaseActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    changeFragment(HomeFragment.newInstance(), fm);
                    return true;
                case R.id.navigation_dashboard:
                    changeFragment(FindFragment.newInstance(), fm);
                    return true;
                case R.id.navigation_notifications:
                    changeFragment(MyFragment.newInstance(), fm);
                    return true;
            }
            return false;
        }
    };

    private FragmentManager fm;
    private Fragment lastFragment;

    private Unbinder unbinder;

    @BindView(R.id.tl_root)
    Toolbar tlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        unbinder = ButterKnife.bind(this);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        tlRoot.setTitle("Bilibili工具箱");
        tlRoot.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(tlRoot);
        setInit();

    }

    @Override
    protected void initView() {
        super.initView();

        fm = getSupportFragmentManager();
        changeFragment(HomeFragment.newInstance(), fm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tootal_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                ToastUtils.showToast(this,"建设中...",ToastUtils.TYPE_INFO);
                break;
        }
        return true;
    }

    private void changeFragment(Fragment fragment, FragmentManager fm) {
        if (fragment instanceof HomeFragment) {
            goChangFragment(fragment, fm);
        } else if (fragment instanceof FindFragment) {
            goChangFragment(fragment, fm);
        } else if (fragment instanceof MyFragment) {
            goChangFragment(fragment, fm);
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl, fragment).commit();
    }

    private void goChangFragment(Fragment fragment, FragmentManager fm) {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (lastFragment != fragment) {
            if (lastFragment != null) {
                // 切换动作
                if (fragment.isAdded()) { // 将要切换的 fragment是否被添加过
                    // 已经被添加过了
                    fragmentTransaction.hide(lastFragment).show(fragment).commit();
                } else {
                    // 未添加过
                    fragmentTransaction.hide(lastFragment).add(R.id.fl, fragment).commitAllowingStateLoss();
                }

            } else {
                fragmentTransaction.add(R.id.fl, fragment).commit();
            }
            lastFragment = fragment;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
