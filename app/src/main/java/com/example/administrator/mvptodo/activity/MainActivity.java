package com.example.administrator.mvptodo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mvptodo.R;
import com.example.administrator.mvptodo.base.BaseActivity;
import com.example.administrator.mvptodo.bean.MainIndexBean;
import com.example.administrator.mvptodo.contract.MainContract;
import com.example.administrator.mvptodo.jni.JniHelper;
import com.example.administrator.mvptodo.presenter.MainPresenter;
import com.example.common.image.ImageLodea;
import com.example.common.widget.BannerLayout;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.MainView {


    private TextView mSampleText;
    private TextView mTextView;
    private BannerLayout mBannerLayout;
    private ImageView mImageView;


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
        mImageView = findViewById(R.id.imageView);
        mBannerLayout = findViewById(R.id.bannerLayout);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
//                Bing.init(this)
//                .withApiHost("http://114.67.145.163/RestServer/api/")
//                .withInterceptor(new LoggerInterceptor())
//                .withLoaderDelayed(1000)
//                .configure();
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
        String imageUrl = loginBean.getData().get(1).getImageUrl();

        ImageLodea.image(this,imageUrl,mImageView);

        List<String> banners = loginBean.getData().get(0).getBanners();
        if (mBannerLayout != null && banners!=null||banners.size()>0) {
            mBannerLayout.setViewUrls(banners, null);

        }

    }

    @Override
    public void loginFail(String failMsg) {

    }
}
