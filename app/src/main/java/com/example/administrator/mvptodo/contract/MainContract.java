package com.example.administrator.mvptodo.contract;

import com.example.administrator.mvptodo.bean.MainIndexBean;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 15:31
 * version: ${version}
 * des: 契约类, 定义view 和 presenter 用到的所有接口方法都在这 一目了然，方便维护
 */
public class MainContract {

    public interface MainView {
        void loginSuccess(MainIndexBean loginBean); // 请求成功，展示数据
        void loginFail(String failMsg);
    }

    public interface MainPresenter {
        void index(String url); // 业务逻辑
    }
}
