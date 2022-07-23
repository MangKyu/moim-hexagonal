package com.mangkyu.moim.hexagonal.app.member.common.errors;

import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public class MemberException extends CommonException {

    public MemberException(final MemberErrorCode errorCode) {
        super(LogLevel.INFO, errorCode);
    }

    public MemberException(final LogLevel logLevel, final MemberErrorCode errorCode) {
        super(logLevel, errorCode);
    }

}
