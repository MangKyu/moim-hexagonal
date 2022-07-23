package com.mangkyu.moim.hexagonal.app.errors;

import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public class CommonException extends RuntimeException {

    private final LogLevel logLevel;
    private final ErrorCode errorCode;

    public CommonException(final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.logLevel = LogLevel.WARN;
        this.errorCode = errorCode;
    }

    public CommonException(final LogLevel logLevel, final ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.logLevel = logLevel;
        this.errorCode = errorCode;
    }

    public CommonException(final CommonErrorCode errorCode, final Exception e) {
        super(errorCode.getMessage(), e);
        this.logLevel = LogLevel.WARN;
        this.errorCode = errorCode;
    }

}