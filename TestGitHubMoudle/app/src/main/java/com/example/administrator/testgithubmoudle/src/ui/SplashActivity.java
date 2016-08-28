package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.MainActivity;

/**
 * Created by Administrator on 2016/8/27.
 */
public class SplashActivity extends FragmentActivity{

    private ImageView mImageView;
    private static final int START_MAIN = 1;

    private class MyHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            if (!isFinishing()) {
                switch (msg.what) {
                    case START_MAIN:
                        startMainActivity();
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
    }

    private final Handler myHandler = new Handler(new MyHandler());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.splash);

        if (null != getActionBar()) {
            getActionBar().hide();
        }

        //start main
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
            }
        }, 800);
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
