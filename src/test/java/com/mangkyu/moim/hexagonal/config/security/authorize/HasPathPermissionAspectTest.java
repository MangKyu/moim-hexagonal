package com.mangkyu.moim.hexagonal.config.security.authorize;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;

import static com.mangkyu.moim.hexagonal.app.login.LoginTestSource.loginTokenClaims;
import static com.mangkyu.moim.hexagonal.config.security.authenticate.TokenConstants.TOKEN_CLAIMS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@ExtendWith(MockitoExtension.class)
class HasPathPermissionAspectTest {

    private HasPathPermissionAspect target;

    private ProceedingJoinPoint joinPoint;
    private MethodSignature methodSignature;

    @BeforeEach
    void setUp() {
        joinPoint = mock(ProceedingJoinPoint.class);
        methodSignature = mock(MethodSignature.class);

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        target = new HasPathPermissionAspect();
    }

    @Test
    void PathVariable을못찾음() {
        doReturn(new String[]{"noneId"})
                .when(methodSignature)
                .getParameterNames();

        doReturn(methodSignature)
                .when(joinPoint)
                .getSignature();

        final CommonException result = assertThrows(CommonException.class, () -> target.aspectParameter(joinPoint, hasPathPermission()));
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.INVALID_PARAMETER);
    }

    @Test
    void 토큰정보가없음() {
        doReturn(new String[]{"id"})
                .when(methodSignature)
                .getParameterNames();

        doReturn(new Long[]{1L})
                .when(joinPoint)
                .getArgs();

        doReturn(methodSignature)
                .when(joinPoint)
                .getSignature();

        final CommonException result = assertThrows(CommonException.class, () -> target.aspectParameter(joinPoint, hasPathPermission()));
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.UNAUTHORIZED);
    }

    @Test
    void 아이디가매칭되지않음() {
        doReturn(new String[]{"id"})
                .when(methodSignature)
                .getParameterNames();

        doReturn(new Long[]{2L})
                .when(joinPoint)
                .getArgs();

        doReturn(methodSignature)
                .when(joinPoint)
                .getSignature();

        RequestContextHolder.currentRequestAttributes().setAttribute(TOKEN_CLAIMS, loginTokenClaims(), SCOPE_REQUEST);

        final CommonException result = assertThrows(CommonException.class, () -> target.aspectParameter(joinPoint, hasPathPermission()));
        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.FORBIDDEN);
    }

    @Test
    void 요청성공() throws Throwable {
        doReturn(new String[]{"id"})
                .when(methodSignature)
                .getParameterNames();

        doReturn(new Long[]{1L})
                .when(joinPoint)
                .getArgs();

        doReturn(methodSignature)
                .when(joinPoint)
                .getSignature();

        RequestContextHolder.currentRequestAttributes().setAttribute(TOKEN_CLAIMS, loginTokenClaims(), SCOPE_REQUEST);

        target.aspectParameter(joinPoint, hasPathPermission());

        verify(joinPoint, times(1)).proceed();
    }

    private HasPathPermission hasPathPermission() {
        return new HasPathPermission() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return HasPathPermission.class;
            }

            @Override
            public String value() {
                return "id";
            }
        };
    }

}