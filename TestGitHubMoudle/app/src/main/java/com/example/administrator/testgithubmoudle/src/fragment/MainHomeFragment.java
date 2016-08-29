package com.example.administrator.testgithubmoudle.src.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.MainActivity;
import com.example.administrator.testgithubmoudle.src.ui.BaseActivity;

import java.io.File;

/**
 * Created by Administrator on 2016/8/27.
 */
public class MainHomeFragment extends Fragment {

    private TextView mNumTextView;
    private ImageView mSelectPicView;
    private ImageView mOpenCameraView;

    private Uri fileUri;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    private static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 2;

    private Activity mContext;

    public MainHomeFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        mContext = activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.main_home_fragment_layout, null);
        initView(contentView);
        initData();
        initListener();
        return contentView;
    }

    private void initView(View view) {
        mNumTextView = (TextView) view.findViewById(R.id.main_enter_num);
        mSelectPicView = (ImageView) view.findViewById(R.id.main_select_pic);
        mOpenCameraView = (ImageView) view.findViewById(R.id.main_open_camera);
    }

    private void initListener() {
        mSelectPicView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicFolder();
            }
        });

        mOpenCameraView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

    }

    private void initData() {
        //todo set num
    }

    public void openPicFolder() {
        //if startActivity here directly, the resultCode which we get from onActivityResult() is not the one we set
        //for now we don't know why
        ((BaseActivity)mContext).openPicFolder();
    }

    public void openCamera() {
        ((BaseActivity)mContext).openCamera();
    }

}
