package com.example.administrator.mvptodo.jni;

/**
 * @autour: wanbin
 * date: 2018/1/4 0004 14:37
 * version: ${version}
 * des:
 */
public class JniHelper {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }



    private JniHelper() {
    }
    private static class SingletonHolder {
        private static final JniHelper INSTANCE = new JniHelper();
    }
    public static JniHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
