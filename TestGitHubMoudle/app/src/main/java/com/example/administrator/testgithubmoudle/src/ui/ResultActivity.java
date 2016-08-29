package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.MainActivity;
import com.example.administrator.testgithubmoudle.src.adapter.ResultImageAdapter;
import com.example.administrator.testgithubmoudle.src.utils.StringUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/27.
 */
public class ResultActivity extends BaseActivity {

    private static ArrayList<String> mImageList;
    private ResultImageAdapter mAdapter;
    private ListView mList;
    private TitleBar mTitleBar;
    private Button mContinueBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity_layout);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mList = (ListView) findViewById(R.id.result_list_view);
        mContinueBtn = (Button) findViewById(R.id.result_button);
        mTitleBar = (TitleBar) findViewById(R.id.result_title_bar);
    }

    private void initData() {
        if (null == mImageList) {
            mImageList = new ArrayList<>();
        }
        if (null != getIntent() ) {
            String imgPath = getIntent().getStringExtra(IMAGE_PATH);
            if (!StringUtil.isEmptyOrNull(String.valueOf(imgPath))) {
                mImageList.add(imgPath);
            }

            boolean isClean = getIntent().getBooleanExtra(IS_FROM, false);
            if (!isClean) {
                mImageList.clear();
            }
        }

        mAdapter = new ResultImageAdapter(this, mImageList);
        mList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mTitleBar.setTitle(getResources().getString(R.string.result_title));
        mTitleBar.showBackBtn(false);
        mTitleBar.showShareBtn(false);
    }

    private void initListener() {
        mTitleBar.setTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mContinueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, ContinueSelectActivity.class);
                startActivity(intent);
            }
        });
    }


}
