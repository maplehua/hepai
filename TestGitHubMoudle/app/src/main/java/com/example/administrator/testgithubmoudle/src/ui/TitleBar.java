package com.example.administrator.testgithubmoudle.src.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.testgithubmoudle.R;
import com.example.administrator.testgithubmoudle.src.utils.StringUtil;

/**
 * Created by Administrator on 2016/8/27.
 */
public class TitleBar extends RelativeLayout {
    private Context mContext;
    private TextView mTitle;
    private ImageView mShare;
    private ImageView mBack;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.titile_bar_layout, this);
        mTitle = (TextView) findViewById(R.id.title_bar_title);
        mShare = (ImageView) findViewById(R.id.title_bar_share);
        mBack = (ImageView) findViewById(R.id.title_bar_back);
    }

    public void setTitle(String title) {
        if (null != mTitle && !StringUtil.isEmptyOrNull(title)) {
            mTitle.setText(title);
        }
    }

    public void showBackBtn(boolean flag) {
        if (null != mBack) {
            if (flag) {
                mBack.setVisibility(VISIBLE);
            } else {
                mBack.setVisibility(GONE);
            }
        }
    }

    public void showShareBtn(boolean flag) {
        if (null != mShare) {
            if (flag) {
                mShare.setVisibility(VISIBLE);
            } else {
                mShare.setVisibility(GONE);
            }
        }
    }

    public void setBackBtnListener(OnClickListener listener) {
        if (null != mBack) {
            mBack.setOnClickListener(listener);
        }
    }

    public void setShareBtnListener(OnClickListener listener) {
        if (null != mShare) {
            mShare.setOnClickListener(listener);
        }
    }
}
