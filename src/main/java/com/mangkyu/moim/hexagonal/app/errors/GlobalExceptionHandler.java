package com.mangkyu.moim.hexagonal.app.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String HANDLE_COMMON_EXCEPTION_MESSAGE = "handleCommonException";

    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorResponse> handleCommonException(final CommonException e) {
        printLog(e);
        return handleExceptionInternal(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(final Exception e) {
        log.warn("handleAllException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR, CommonErrorCode.INTERNAL_SERVER_ERROR.getMessage());
    }

    private ResponseEntity<ErrorResponse> handleExceptionInternal(final ErrorCode errorCode, final String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.of(errorCode.name(), message));
    }

    private void printLog(final CommonException e) {
        if (e.getLogLevel() == LogLevel.ERROR) {
            log.error(HANDLE_COMMON_EXCEPTION_MESSAGE, e);
        } else if (e.getLogLevel() == LogLevel.WARN) {
            log.warn(HANDLE_COMMON_EXCEPTION_MESSAGE, e);
        } else if (e.getLogLevel() == LogLevel.DEBUG) {
            log.debug(HANDLE_COMMON_EXCEPTION_MESSAGE, e);
        } else if (e.getLogLevel() == LogLevel.TRACE) {
            log.trace(HANDLE_COMMON_EXCEPTION_MESSAGE, e);
        } else {
            log.info(HANDLE_COMMON_EXCEPTION_MESSAGE, e);
        }
    }

    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        super.handleExceptionInternal(ex, body, headers, status, request);

        return ResponseEntity.status(status)
                .headers(headers)
                .body(ErrorResponse.of(status.name(), ex.getMessage()));
    }

}