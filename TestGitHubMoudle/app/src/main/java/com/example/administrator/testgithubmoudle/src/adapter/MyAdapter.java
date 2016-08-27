package com.example.administrator.testgithubmoudle.src.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.testgithubmoudle.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/25.
 */
public class MyAdapter extends BaseAdapter {

    private ArrayList<String> mData;
    private Context mContext;

    public  MyAdapter(Context context, ArrayList<String> data) {
        mContext = context;
        mData = data;
    }
    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        if (mData != null) {
            if (position >= 0 && position < mData.size()) {
                return mData.get(position);
            }
            return null;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.str = (TextView) convertView.findViewById(R.id.list_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.str.setText(mData.get(position));
        return convertView;
    }
}

class ViewHolder {
    TextView str;
}
