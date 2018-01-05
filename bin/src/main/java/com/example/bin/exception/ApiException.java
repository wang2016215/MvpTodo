package com.example.bin.exception;

/**
 * HTTP错误
 *
 * @author gc
 * @since 1.0
 */
public class ApiException extends RuntimeException {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}