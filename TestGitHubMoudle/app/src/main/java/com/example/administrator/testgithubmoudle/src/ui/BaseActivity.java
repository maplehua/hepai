package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.administrator.testgithubmoudle.src.camera.CameraManager;
import com.example.administrator.testgithubmoudle.src.utils.ImageUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/29.
 */
public class BaseActivity extends FragmentActivity {

    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0x12;
    protected static final int SELECT_IMAGE_ACTIVITY_REQUEST_CODE = 0x13;

    protected Uri fileUri;

    public static final String IMAGE_PATH = "image_path";
    public static final String IS_FROM = "is_from";

    protected ArrayList<Uri> mCameraList;

    private boolean isFromContinue = false;

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

        if (null == mCameraList) {
            mCameraList = new ArrayList<>();
        }
        if (!mCameraList.contains(fileUri)) {
            mCameraList.add(fileUri);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //http://www.jb51.net/article/32939.htm
        //requestCode is not the code I set if not onActivityResult in the activity called startActivity
        try {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    // Image captured and saved to fileUri specified in the Intent
                    Log.e("maple", "camera succeed!  " + (data == null ? "data == null" : data.getData()));
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra(IMAGE_PATH, String.valueOf(fileUri));
                    intent.putExtra(IS_FROM, isFromContinue);
                    startActivity(intent);
                } else if (resultCode == RESULT_CANCELED) {
                    // User cancelled the image capture
                    Log.e("maple", "camera cancelled!");
                }
            } else if (requestCode == SELECT_IMAGE_ACTIVITY_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    Log.e("maple", "select pic result");
                    Uri uri = data.getData();
                    Cursor cursor = getContentResolver().query(uri, null,
                            null, null, null);
                    cursor.moveToFirst();
                    String imgNo = cursor.getString(0); // 图片编号
                    String imgPath = cursor.getString(1); // 图片文件路径
                    String imgSize = cursor.getString(2); // 图片大小
                    String imgName = cursor.getString(3); // 图片文件名
                    cursor.close();

//                    Bitmap bitmap = ImageUtil.readBitmapFromFile(imgPath);
                    Log.d("maple", "select pic path = " + imgPath);

                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra(IMAGE_PATH, imgPath);
                    intent.putExtra(IS_FROM, isFromContinue);
                    startActivity(intent);
                }
            }

        } catch (Exception e) {
            Log.e("maple", "onActivityResult error");
        }
    }

    public void setFromContinue(boolean fromContinue) {
        isFromContinue = fromContinue;
    }
}
