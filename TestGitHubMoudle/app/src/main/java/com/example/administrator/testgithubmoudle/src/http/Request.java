package com.example.administrator.testgithubmoudle.src.http;

import com.example.administrator.testgithubmoudle.src.utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/25.
 */
public class Request {

    private okhttp3.Request mRequest;
    private Map<String, String> mUrlParams;
    private String mUrl;

    public void setUrl(String url) {
        mUrl = url;
    }

    public void setParams(final String key, final String value) {
        if (mUrlParams == null) {
            mUrlParams = new HashMap<String, String>();
        }
        mUrlParams.put(key, value);
    }

    public okhttp3.Request build() {
        if (!StringUtil.isEmptyOrNull(mUrl)) {
            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();

            if (mUrlParams != null && mUrlParams.size() > 0) {
                for (String key : mUrlParams.keySet()) {
                    if (mUrl.contains("?")) {
                        mUrl = mUrl + "&" + key + "=" + mUrlParams.get(key);
                    } else {
                        mUrl = mUrl + "?" + key + "=" + mUrlParams.get(key);
                    }
                }
            }
            builder.url(mUrl);
            mRequest = builder.build();
        }
        return mRequest;
    }
}
