package com.snwlee.springbasetemplate.common.entity;

import lombok.Builder;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
public class BaseResponse<T> {

    private final T data;
    private final int status;

    @Builder
    private BaseResponse(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data, OK.value());
    }

    public static <T> BaseResponse<T> unauthorized(T data) {
        return new BaseResponse<>(data, UNAUTHORIZED.value());
    }

    public static <T> BaseResponse<T> success() {
        return new BaseResponse<>(null, OK.value());
    }

    public static <T> BaseResponse<T> created() {
        return new BaseResponse<>(null, CREATED.value());
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<>(data, BAD_REQUEST.value());
    }

    public static <T> BaseResponse<T> internalServerError(T data) {
        return new BaseResponse<>(data, INTERNAL_SERVER_ERROR.value());
    }
}
