package com.example.administrator.mvptodo.model;

import com.example.administrator.mvptodo.BaseApplication;
import com.example.administrator.mvptodo.base.BaseModel;
import com.example.administrator.mvptodo.bean.MainIndexBean;
import com.example.bin.exception.ApiException;
import com.example.bin.observer.CommonObserver;
import com.example.bin.retrofit.RestCreator;
import com.example.bin.rx.RxSchedulers;
import com.google.gson.Gson;

import java.util.WeakHashMap;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 15:13
 * version: ${version}
 * des: Model, 主要做一些数据处理, 网路请求
 */
public class MainModel extends BaseModel{
    private boolean isLogin = false;
    public boolean loadIndex(String url,final DataListener listener){
        if (listener == null) {
            throw new RuntimeException("InfoHint不能为空");
        }

        final WeakHashMap<String, Object> params = new WeakHashMap<>();

        RestCreator.getRxRestService().get(url,params)
                .compose(RxSchedulers.<String>compose())
                .subscribe(new CommonObserver<String>(BaseApplication.getContext()) {
                    @Override
                    public void onNext(String s) {

                        MainIndexBean bean = new Gson().fromJson(s,MainIndexBean.class);

                        listener.successInfo(bean);
                        isLogin = true;
                    }

                    @Override
                    protected void onError(ApiException e) {
                      listener.failInfo(e.message);

                    }
                });

        return isLogin;
    }

    /**
     * 通过接口产生信息回调
     *
     * @param <T>
     */
    public interface DataListener<T> {
        void successInfo(T result);
        void failInfo(String result);
    }
}
