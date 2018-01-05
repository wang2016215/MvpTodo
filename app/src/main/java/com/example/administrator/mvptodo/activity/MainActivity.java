package com.example.administrator.mvptodo.activity;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.mvptodo.R;
import com.example.administrator.mvptodo.base.BaseActivity;
import com.example.administrator.mvptodo.bean.MainIndexBean;
import com.example.administrator.mvptodo.contract.MainContract;
import com.example.administrator.mvptodo.jni.JniHelper;
import com.example.administrator.mvptodo.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {


    private TextView mSampleText;
    private TextView mTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresent() {
        return new MainPresenter();
    }

    @Override
    protected void initView() {
        mSampleText = findViewById(R.id.sample_text);

        mTextView = findViewById(R.id.tv_jni);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        getP().index("index.php");
        mTextView.setText(JniHelper.getInstance().stringFromJNI());
    }

    @Override
    protected void otherViewClick(View view) {

    }


    @Override
    public void showLoading() {
        toast("加载数据中.....");
    }

    @Override
    public void loginSuccess(MainIndexBean loginBean) {
        mSampleText.setText(loginBean.getData().get(1).getText());


    }

    @Override
    public void loginFail(String failMsg) {

    }
}
