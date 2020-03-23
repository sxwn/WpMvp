package com.xiaowei.wpmvp;

import android.app.Application;

/**
 * 切记不要用 instance = new MyApp() 一类的赋值去获取实例，这样你得到的只是一个普通的 Java 类，不会具备任何 Application 的功能！
 */

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
