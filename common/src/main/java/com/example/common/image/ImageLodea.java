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
public class ImageLodea {

    public static void image(Context context, String url, ImageView imageView){
                Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
