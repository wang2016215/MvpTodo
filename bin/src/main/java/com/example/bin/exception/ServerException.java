package com.example.bin.exception;

/**
 * 服务器错误
 *
 * @author gc
 * @since 1.0
 */
public class ServerException extends RuntimeException {
    public int code;
    public String message;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}