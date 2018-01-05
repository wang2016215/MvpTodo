package com.example.bin.callback;

import android.os.Handler;
import android.support.annotation.NonNull;

import com.example.bin.app.Bing;
import com.example.bin.app.ConfigKeys;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @autour: wanbin
 * date: 2017/12/7 0007 11:04
 * version: ${version}
 * des:
 */

public final class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private static final Handler HANDLER = Bing.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;

    }

    @Override
    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        onRequestFinish();
    }

    @Override
    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        onRequestFinish();
    }

    private void onRequestFinish() {
        final long delayed = Bing.getConfiguration(ConfigKeys.LOADER_DELAYED);

    }
}
