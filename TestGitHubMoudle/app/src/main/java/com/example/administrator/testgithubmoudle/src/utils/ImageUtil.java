package com.example.administrator.testgithubmoudle.src.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ImageUtil {
    public static Bitmap readBitmapFromFile(String imgPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 10;
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
        return bitmap;
    }
}
