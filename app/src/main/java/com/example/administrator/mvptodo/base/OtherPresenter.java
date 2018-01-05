package com.example.administrator.mvptodo.base;


import com.example.administrator.mvptodo.mvp.IModel;
import com.example.administrator.mvptodo.mvp.IPresenter;
import com.example.administrator.mvptodo.mvp.IView;

import java.lang.ref.WeakReference;

/**
 * 其他的一些Presenter基类
 *
 * @author gc
 * @since 1.0
 */
public abstract class OtherPresenter<M extends IModel, V extends IView> implements IPresenter {
    private WeakReference actReference;
    protected V iView;
    protected M iModel;

    public M getiModel() {
        iModel = loadModel(); //使用前先进行初始化
        return iModel;
    }

    @Override
    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
    }

    @Override
    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    @Override
    public V getIView() {
        return (V) actReference.get();
    }

    public abstract M loadModel();
}
