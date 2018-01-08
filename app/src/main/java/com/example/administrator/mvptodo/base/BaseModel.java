package com.example.administrator.mvptodo.base;


import com.example.administrator.mvptodo.http.HttpHolder;
import com.example.administrator.mvptodo.mvp.IModel;
import com.example.bin.rx.RxRestService;

/**
 * Model基类
 *
 * @author gc
 * @since 1.0
 */
public class BaseModel implements IModel {

    static {
       sRxRestService = HttpHolder.getRxRestService();
   }
    protected static RxRestService sRxRestService;
}
