package com.example.administrator.testgithubmoudle.src.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.utils.ImageUtil;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/29.
 */
public class ResultImageAdapter extends BaseAdapter {

    private ArrayList<String> mData;// image path list
    private Context mContext;

    public ResultImageAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;
    }
    @Override
    public int getCount() {
        int count = 0;
        if (null != mData) {
            count = mData.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        String res = null;
        if (null != mData && position >= 0 && position < mData.size()) {
            res = mData.get(position);
        }
        return res;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.result_list_item_layout, null);
            holder = new ViewHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.list_item_img);
            convertView.setTag(holder);
        } else {
           holder = (ViewHolder) convertView.getTag();
        }
        Log.d("maple", "getView here. position = " + position + (null == mData ? " mData == null" : "mData.size() = " + mData.size()));
        if (null != getItem(position)) {
            Log.d("maple", "getItem(position) = " + getItem(position));
            String imgPath = String.valueOf(getItem(position));
            if (null != imgPath && imgPath.contains("file://")) {
                imgPath = imgPath.replace("file://", "");
            }
            Bitmap bitmap = ImageUtil.readBitmapFromFile(imgPath);
            Log.d("maple", (bitmap == null ? "bitmap = null" : bitmap.toString()));
            holder.mImageView.setImageBitmap(bitmap);
        }
        return convertView;
    }

    class ViewHolder {
        public ImageView mImageView;
    }
}
