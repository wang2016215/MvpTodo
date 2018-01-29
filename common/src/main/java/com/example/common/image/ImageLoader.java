package com.example.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @autour: wanbin
 * date: 2018/1/26 0026 10:59
 * version: ${version}
 * des:
 */
public class ImageLoader {


    private ImageLoader() {
    }
    private static class ImageHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }
    public static ImageLoader getInstance() {
        return ImageHolder.INSTANCE;
    }


    /***
     * 加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public void image(Context context, String url, ImageView imageView){
                Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
