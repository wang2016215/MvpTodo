package com.example.bin.observer;


import com.example.bin.exception.ApiException;
import com.example.bin.exception.ExceptionEngine;

import io.reactivex.Observer;

/**
 * 观察者基类
 *
 * @author gc
 * @since 1.0
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {

        ApiException apiException = ExceptionEngine.handleException(e);
        onError(apiException);
    }

    /**
     * @param e 错误的一个回调
     */
    protected abstract void onError(ApiException e);
}