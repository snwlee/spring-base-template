package com.pgrrr.springbasetemplate.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum ErrorCode {

    ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "item not found"),

    DUPLICATE_ITEM(HttpStatus.BAD_REQUEST, "duplicate item");

    public static final String ERROR_CODE_PREFIX = "ERR_";
    public static final String ERROR_CODE_SUFFIX = "_CODE";
    private final HttpStatus httpStatus;
    private final String message;

    public String getCode() {
        return ERROR_CODE_PREFIX + this.name() + ERROR_CODE_SUFFIX;
    }
}
