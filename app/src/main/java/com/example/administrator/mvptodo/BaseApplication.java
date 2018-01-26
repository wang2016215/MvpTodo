package com.example.administrator.mvptodo;

import android.app.Application;
import android.content.Context;

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

        /**
         * 检测app启动耗时的方法   Debug.startMethodTracing(file.getAbsolutePath());
         *   Debug.stopMethodTracing(); 成对出现
         *
         *  从启动时间的角度 application 不建议初始化太多的东西
         *   初始化okhttp 等东西会导致app启动时间增长
         */
//        File file = new File(Environment.getExternalStorageDirectory(),"app3");
//        LogUtils.d("wangbin",file.getAbsolutePath());
//        Debug.startMethodTracing(file.getAbsolutePath());
        mContext = this;



//        Debug.stopMethodTracing();
    }

    /**
     * @return 全局的上下文
     */
    public static Context getContext() {
        return mContext;
    }
}
