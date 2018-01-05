package com.example.bin.app;

import android.content.Context;
import android.os.Handler;

/**
 * @author wanbin
 *
 * date: 2017/12/7 0007 10:51
 * version: ${version}
 * des:
 */
public class Bing {

    public static Configurator init (Context context){
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT,context.getApplicationContext());

             return Configurator.getInstance();
    }
    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext() {
        return getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
