package com.example.administrator.testgithubmoudle.src.utils;

import com.example.administrator.testgithubmoudle.src.MainActivity;
import com.example.administrator.testgithubmoudle.src.http.OkHttpManager;
import com.example.administrator.testgithubmoudle.src.http.Request;

import okhttp3.Callback;

/**
 * Created by Administrator on 2016/8/25.
 */
public class RequestManager {

    public static void startTestRequest(Callback callback) {
        Request request = new Request();
        request.setUrl("http://r.cnews.qq.com/cellInfoFinanceNew");
        OkHttpManager.startHttpRequest(request.build(), callback);
    }
}
