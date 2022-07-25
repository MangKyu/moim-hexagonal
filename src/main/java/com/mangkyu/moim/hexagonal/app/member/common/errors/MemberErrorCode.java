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

    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND, "Member is not exists"),
    DUPLICATE_LOGINID(HttpStatus.BAD_REQUEST, "Email is duplicated"),
    DUPLICATE_ROLE(HttpStatus.BAD_REQUEST, "Role is duplicated"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}