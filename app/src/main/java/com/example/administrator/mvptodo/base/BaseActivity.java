package com.example.administrator.mvptodo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.mvptodo.mvp.IView;
import com.example.administrator.mvptodo.utils.LogUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Activity基类
 *
 * @author gc
 * @since 1.0
 */
public abstract class BaseActivity<P extends OtherPresenter> extends RxAppCompatActivity implements
        IView, View.OnClickListener {
    protected View view;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mPresenter = createPresent();
        initCommonData();
        initView();
        initListener();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract P createPresent();

    private void initCommonData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void initView();

    protected abstract void initListener();

    protected abstract void initData();

    protected abstract void otherViewClick(View view);

    /**
     * @return 显示的内容
     */
    public View getView() {
        view = View.inflate(this, getLayoutId(), null);
        return view;
    }

    /**
     * 点击的事件的统一的处理
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                otherViewClick(view);
                break;
        }
    }

    /**
     * 显示一个内容为str的toast
     *
     * @param str
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示一个内容为contentId指定的toast
     *
     * @param contentId
     */
    public void toast(int contentId) {
        Toast.makeText(this, contentId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 日志的处理
     *
     * @param str
     */
    public void LogE(String str) {
        LogUtils.e(getClass(), str);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
