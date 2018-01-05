package com.example.administrator.mvptodo;

import android.app.Application;
import android.content.Context;

import com.example.bin.app.Bing;
import com.example.bin.interceptors.LoggerInterceptor;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 14:50
 * version: ${version}
 * des:
 */
public class BaseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        Bing.init(this)
                .withApiHost("http://114.67.145.163/RestServer/api/")
                .withInterceptor(new LoggerInterceptor())
                .withLoaderDelayed(1000)
                .configure();
    }

    /**
     * @return 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
