package com.mangkyu.moim.hexagonal.config.security;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter target;
    private ParseLoginTokenUseCase parseLoginTokenUseCase;
    private ServletRequest servletRequest;
    private String headerToken;

    @BeforeEach
    void setUp() {
        parseLoginTokenUseCase = mock(ParseLoginTokenUseCase.class);
        target = new JwtAuthenticationFilter(parseLoginTokenUseCase);

        headerToken = "Bearer " + "token";
        servletRequest = new MockHttpServletRequest();
        ((MockHttpServletRequest)servletRequest).addHeader("Authorization", headerToken);
    }

    @Test
    void 이메일이이존재하지않음() {
        doReturn(null)
                .when(parseLoginTokenUseCase)
                .parseEmail(headerToken);

        final CommonException result = assertThrows(
                CommonException.class,
                () -> target.doFilter(servletRequest, null, null));

        assertThat(result.getErrorCode()).isEqualTo(CommonErrorCode.UNAUTHORIZED);
    }

    @Test
    void 유효하지않은토큰() throws ServletException, IOException {
        final FilterChain filterChain = mock(FilterChain.class);

        doReturn("email")
                .when(parseLoginTokenUseCase)
                .parseEmail(headerToken);

        target.doFilter(servletRequest, null, filterChain);

        verify(filterChain).doFilter(any(), any());
    }

}