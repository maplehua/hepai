package com.example.administrator.testgithubmoudle.src.utils;

import android.graphics.Bitmap;

import com.example.administrator.testgithubmoudle.src.MainActivity;
import com.example.administrator.testgithubmoudle.src.http.OkHttpManager;
import com.example.administrator.testgithubmoudle.src.http.Request;

import java.util.ArrayList;

import okhttp3.Callback;

/**
 * Created by Administrator on 2016/8/25.
 */
public class RequestManager {

    public static void startImageRequest(Callback callback, ArrayList<String> imgList) {
        Request request = new Request();
        request.setUrl("http://10.72.144.113:8080/HepaiServer/PictureFileUpload");
        for (String str : imgList) {
            Bitmap bitmap = ImageUtil.readBitmapFromFile(str);
            request.setParams("picture", String.valueOf(bitmap));
        }
        OkHttpManager.startHttpRequest(request.build(), callback);
    }
}
