package com.example.bin.exception;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 封装Transformer
 *
 * @author gc
 * @since 1.0
 */
public class CommonTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {
    @Override
    public ObservableSource<T> apply(@NonNull Observable<BaseHttpResult<T>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) // UI线程
                .compose(ErrorTransformer.<T>getInstance());
    }
}