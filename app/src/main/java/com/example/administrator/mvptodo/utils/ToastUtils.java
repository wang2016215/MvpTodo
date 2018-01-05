package com.example.administrator.mvptodo.utils;

import android.widget.Toast;

import com.example.administrator.mvptodo.BaseApplication;

/**
 * Toast
 *
 * @author gc
 * @since 1.1
 */
public class ToastUtils {

    public static Toast toast;

    private ToastUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void showShort(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
