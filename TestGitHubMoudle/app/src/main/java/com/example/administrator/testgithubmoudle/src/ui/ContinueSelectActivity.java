package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.fragment.MainHomeFragment;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ContinueSelectActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.continue_selecdt_activity_layout);
        initView();
    }

    private void initView() {
        MainHomeFragment mainFragment = new MainHomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, mainFragment).commit();
        setFromContinue(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        finish();
    }
}
