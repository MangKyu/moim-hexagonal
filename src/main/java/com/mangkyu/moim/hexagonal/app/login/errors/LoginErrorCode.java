package com.mangkyu.moim.hexagonal.app.login.errors;

import com.mangkyu.moim.hexagonal.app.errors.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@ToString
public enum LoginErrorCode implements ErrorCode {

    INVALID_EMAIL_OR_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid email or passwrd"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}