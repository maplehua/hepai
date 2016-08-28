package com.example.administrator.testgithubmoudle.src.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.Application;
import com.example.administrator.testgithubmoudle.src.camera.CameraManager;

/**
 * Created by Administrator on 2016/8/27.
 */
public class MainHomeFragment extends Fragment {

    private TextView mNumTextView;
    private ImageView mSelectPicView;
    private ImageView mOpenCameraView;

    private Uri fileUri;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0x12;
    private static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 0x13;

    private Activity mContext;

    public MainHomeFragment() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mContext = (Activity) bundle.get("context");
        }
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
//        Intent intent = new Intent();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, SELECT_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void openCamera() {
        //        CameraManager.test();


        // create Intent to take a picture and return control to the calling application
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = CameraManager.getOutputMediaFileUri(CameraManager.MEDIA_TYPE_IMAGE); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        //调取前置摄像头
        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        intent.putExtra("camerasensortype", 3);
        // 自动对焦
        intent.putExtra("autofocus", true);
        // start the image capture Intent
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE); // MainActivity
    }

}
