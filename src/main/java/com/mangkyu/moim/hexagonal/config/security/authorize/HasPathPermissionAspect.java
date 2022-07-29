package com.mangkyu.moim.hexagonal.config.security.authorize;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginMember;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import static com.mangkyu.moim.hexagonal.config.security.authenticate.TokenConstants.TOKEN_CLAIMS;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@Aspect
@Component
@Slf4j
public class HasPathPermissionAspect {

    @Around("@annotation(hasPathPermission)")
    public Object aspectParameter(final ProceedingJoinPoint joinPoint, final HasPathPermission hasPathPermission) throws Throwable {
        final Long pathValue = findPathValue(joinPoint, hasPathPermission);
        final LoginMember tokenClaims = getLoginTokenClaims();

        if (tokenClaims == null) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }

        if (!tokenClaims.isIdMatches(pathValue)) {
            throw new CommonException(CommonErrorCode.FORBIDDEN);
        }

        return joinPoint.proceed();
    }

    private LoginMember getLoginTokenClaims() {
        return (LoginMember) RequestContextHolder.currentRequestAttributes().getAttribute(TOKEN_CLAIMS, SCOPE_REQUEST);
    }

    private Long findPathValue(final ProceedingJoinPoint joinPoint, final HasPathPermission hasPathPermission) {
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        final String[] parameterNames = methodSignature.getParameterNames();
        final String key = hasPathPermission.value();

        for (int i = 0; i < parameterNames.length; i++) {
            if (key.equals(parameterNames[i])) {
                return (Long) joinPoint.getArgs()[i];
            }
        }

        throw new CommonException(CommonErrorCode.INVALID_PARAMETER);
    }

}
