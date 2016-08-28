package com.example.administrator.testgithubmoudle.src.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.administrator.testgithubmoudle.src.Application;

/**
 * Created by Administrator on 2016/8/28.
 */
public class MobileUtils {
    public static int dpToPx(float dp) {
        return (int) (dp * MobileUtils.getDeviceDisplayMetrics(Application.getInstance()).density + 0.5f);
    }

    public static DisplayMetrics getDeviceDisplayMetrics(Application application) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) application.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
