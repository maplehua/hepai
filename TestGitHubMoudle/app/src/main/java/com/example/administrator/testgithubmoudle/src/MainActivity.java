package com.example.administrator.testgithubmoudle.src;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.camera.CameraManager;
import com.example.administrator.testgithubmoudle.src.utils.RequestManager;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends Activity implements Callback{

    private PullToRefreshListView mListView;
    private ImageView mImageView;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RequestManager.startTestRequest(this);

        mImageView = (ImageView) findViewById(R.id.imageview);

        openPicFolder();

    }

    public void openPicFolder() {
        //        Intent intent = new Intent();
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
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
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //http://www.jb51.net/article/32939.htm
        if (null != data) {
            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                if (resultCode == RESULT_OK) {
                    // Image captured and saved to fileUri specified in the Intent
                    Toast.makeText(this, "Image saved to:\n" +
                            data.getData(), Toast.LENGTH_LONG).show();
                } else if (resultCode == RESULT_CANCELED) {
                    // User cancelled the image capture
                } else {
                    // Image capture failed, advise user
                }
            } else if (requestCode == 1) {
                Uri uri = data.getData();
                Cursor cursor = getContentResolver().query(uri, null,
                        null, null, null);
                cursor.moveToFirst();
                String imgNo = cursor.getString(0); // 图片编号
                String imgPath = cursor.getString(1); // 图片文件路径
                String imgSize = cursor.getString(2); // 图片大小
                String imgName = cursor.getString(3); // 图片文件名
                cursor.close();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = 10;
                Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
                mImageView.setImageBitmap(bitmap);
            }
        }

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
