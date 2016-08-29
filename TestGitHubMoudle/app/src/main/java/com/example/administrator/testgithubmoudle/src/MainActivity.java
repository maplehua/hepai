package com.example.administrator.testgithubmoudle.src;

import android.graphics.Color;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.adapter.HomeViewPagerAdapter;
import com.example.administrator.testgithubmoudle.src.fragment.MainHomeFragment;
import com.example.administrator.testgithubmoudle.src.fragment.PlatformFragment;
import com.example.administrator.testgithubmoudle.src.ui.BaseActivity;
import com.example.administrator.testgithubmoudle.src.ui.TitleBar;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements Callback{

    private TitleBar mTitleBar;
    private ViewPager mViewPager;
    private FragmentTabHost mTabHost;
    private View mDivLeft;
    private View mDivRight;
    private HomeViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
//        RequestManager.startTestRequest(this);

    }

    private void initData() {
        mAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void initView() {
        mTitleBar = (TitleBar) findViewById(R.id.title_bar);
        mTitleBar.setTitle(getResources().getString(R.string.app_name));
        mTitleBar.showBackBtn(false);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mDivLeft = findViewById(R.id.divide_left);
        mDivRight = findViewById(R.id.divide_right);

        ImageView cameraView = new ImageView(this);
        cameraView.setImageResource(R.drawable.camera);
        ImageView platformView = new ImageView(this);
        platformView.setImageResource(R.drawable.platform);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec("home").setIndicator(cameraView),
                MainHomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("platform").setIndicator(platformView),
                PlatformFragment.class, null);
    }

    public void initListener() {
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if ("home".equals(tabId)) {
                    mViewPager.setCurrentItem(0);
                    showDivide(true, false);
                } else {
                    mViewPager.setCurrentItem(1);
                    showDivide(false, true);
                }

            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showDivide(boolean left, boolean right) {
       if (left) {
           mDivLeft.setBackgroundColor(Color.RED);
       } else {
           mDivLeft.setBackgroundColor(Color.TRANSPARENT);
       }

        if (right) {
            mDivRight.setBackgroundColor(Color.RED);
        } else {
            mDivRight.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public void initPullRefreshListView() {
        //        mListView = (PullToRefreshListView) findViewById(R.id.listview);
//
//        ArrayList<String> list = new ArrayList<>();
//        list.add("Hello");
//        list.add("Test");
//        list.add("Tencent");
//        list.add("OpenSource");
//
//        MyAdapter adapter = new MyAdapter(this, list);
//        mListView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.d("maple", "okHttp--onFailure");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Log.d("maple", "okHttp--onResponse");
        if (response != null && response.isSuccessful()) {
            Log.d("maple", "Response body = " + response.body().string());
        }
    }

}
