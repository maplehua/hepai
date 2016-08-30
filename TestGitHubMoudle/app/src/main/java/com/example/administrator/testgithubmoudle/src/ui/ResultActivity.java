package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.adapter.ResultImageAdapter;
import com.example.administrator.testgithubmoudle.src.utils.RequestManager;
import com.example.administrator.testgithubmoudle.src.utils.StringUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/27.
 */
public class ResultActivity extends BaseActivity implements Callback{

    private static ArrayList<String> mImageList;
    private ResultImageAdapter mAdapter;
    private ListView mList;
    private TitleBar mTitleBar;
    private Button mContinueBtn;
    private Button mCommitBtn;

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
        mCommitBtn = (Button) findViewById(R.id.result_button_complete);
        mTitleBar = (TitleBar) findViewById(R.id.result_title_bar);
    }

    private void initData() {
        if (null == mImageList) {
            mImageList = new ArrayList<>();
        }
        if (null != getIntent() ) {
            boolean isClean = getIntent().getBooleanExtra(IS_FROM, false);
            if (!isClean) {
                mImageList.clear();
            }

            String imgPath = getIntent().getStringExtra(IMAGE_PATH);
            if (!StringUtil.isEmptyOrNull(String.valueOf(imgPath))) {
                mImageList.add(imgPath);
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

        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestManager.startImageRequest(ResultActivity.this, mImageList);
            }
        });
    }


    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Log.d("maple", "okHttp--onResponse");
        if (response != null && response.isSuccessful()) {
            Log.d("maple", "Response body = " + response.body().string());
        }
    }
}
