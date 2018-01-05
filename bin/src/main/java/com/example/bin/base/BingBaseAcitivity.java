package com.example.bin.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * @autour: wanbin
 * date: 2017/12/7 0007 11:36
 * version: ${version}
 * des:
 */
public abstract class BingBaseAcitivity  extends RxAppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }
    protected void showToast(String msg) {
        ToastUtils.showShort(msg);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void init(Bundle savedInstanceState);
}
