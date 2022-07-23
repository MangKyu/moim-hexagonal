package com.mangkyu.moim.hexagonal.app.member.errors;

import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public class MemberException extends CommonException {

    public MemberException(final LogLevel logLevel, final MemberErrorCode errorCode) {
        super(logLevel, errorCode);
    }

}
