package com.example.administrator.mvptodo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mvptodo.R;
import com.example.common.widget.BigView;

import java.io.IOException;
import java.io.InputStream;

public class BigViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);

        BigView bigView = findViewById(R.id.bigView);
        InputStream is = null;
        try {
            //从assets 加载图片
            is = getAssets().open("big.png");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
