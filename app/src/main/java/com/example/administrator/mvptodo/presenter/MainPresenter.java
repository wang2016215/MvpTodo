package com.example.administrator.mvptodo.presenter;

import com.example.administrator.mvptodo.activity.MainActivity;
import com.example.administrator.mvptodo.base.OtherPresenter;
import com.example.administrator.mvptodo.bean.MainIndexBean;
import com.example.administrator.mvptodo.contract.MainContract;
import com.example.administrator.mvptodo.model.MainModel;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 15:30
 * version: ${version}
 * des:Presenter, 交互中间人, 处理View的业务逻辑, 它是沟通View和Model的桥梁
 */
public class MainPresenter extends OtherPresenter<MainModel,MainActivity> implements MainContract.MainPresenter {



    @Override
    public void index(String url) {
        getIView().showLoading();
       loadModel().loadIndex(url, new MainModel.DataListener<MainIndexBean>() {
            @Override
            public void successInfo(MainIndexBean result) {
                getIView().loginSuccess(result);

            }

            @Override
            public void failInfo(String result) {
                getIView().loginFail(result);
            }
        });

    }



//    @Override
//    public HashMap<String, IModel> getiModelMap() {
//        return loadModelMap(new MainModel());
//    }
//
//    @Override
//    public HashMap<String, IModel> loadModelMap(IModel... models) {
//
//
//        HashMap<String, IModel> map = new HashMap<>();
//        map.put("index", models[0]);
//        return map;
//    }

    @Override
    public MainModel loadModel() {
        return new MainModel();
    }
}
