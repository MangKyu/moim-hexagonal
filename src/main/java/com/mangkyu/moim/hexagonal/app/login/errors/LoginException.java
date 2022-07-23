package com.mangkyu.moim.hexagonal.app.login.errors;

import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import lombok.Getter;
import org.springframework.boot.logging.LogLevel;

@Getter
public class LoginException extends CommonException {

    public LoginException(final LogLevel logLevel, final LoginErrorCode errorCode) {
        super(logLevel, errorCode);
    }

}
