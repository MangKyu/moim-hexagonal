package com.mangkyu.moim.hexagonal.app.member.common.errors;

import com.mangkyu.moim.hexagonal.app.errors.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@ToString
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_LOGINID(HttpStatus.BAD_REQUEST, "Email is duplicated"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}