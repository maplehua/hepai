package com.example.administrator.testgithubmoudle.src.http;

import okhttp3.*;

/**
 * Created by Administrator on 2016/8/25.
 */
public class OkHttpManager {

    private final static OkHttpClient mClient = new OkHttpClient();

    public static void startHttpRequest(okhttp3.Request request, Callback callback) {
        if (request != null && callback != null) {
            mClient.newCall(request).enqueue(callback);
        }
    }
}
