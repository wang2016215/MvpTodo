package com.example.administrator.mvptodo.http.rx;

import android.content.Context;

import com.example.administrator.mvptodo.BaseApplication;
import com.example.administrator.mvptodo.utils.NetworkUtil;
import com.example.administrator.mvptodo.utils.ToastUtils;
import com.example.bin.R;
import com.example.bin.observer.BaseObserver;

import io.reactivex.disposables.Disposable;

/**
 * 封装Observer
 *
 * @author gc
 * @since 1.0
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    private static final String TAG = "CommonObserver";
    private Context context;
    // Disposable 相当于RxJava1.x中的 Subscription，用于解除订阅 disposable.dispose();

    protected Disposable disposable;

    public CommonObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
        disposable = d;
        if (!NetworkUtil.isNetworkAvailable(BaseApplication.getContext())) {
           // LogUtils.e(TAG, "网络不可用");
            ToastUtils.showShort(R.string.toast_network_error);
        } else {
           // LogUtils.e(TAG, "网络可用");
        }
    }

//    @Override
//    protected void onError(ApiException e) {
//        //disposable.dispose();
//
//
//        LogUtils.e(TAG, "HTTP错误 --> " + "code:" + e.code + ", message:" + e.message);
//    }

    @Override
    public void onComplete() {
       // LogUtils.e(TAG, "成功了");
       // disposable.dispose();
    }

}