package com.example.administrator.testgithubmoudle.src;

/**
 * Created by Administrator on 2016/8/28.
 */
public class Application extends android.app.Application {

    private static  Application instance = null;

    public static Application getInstance() {
        return instance;
    }

    public Application() {
        instance = this;
    }
}
