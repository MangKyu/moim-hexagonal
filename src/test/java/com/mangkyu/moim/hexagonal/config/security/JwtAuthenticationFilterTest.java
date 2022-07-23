package com.mangkyu.moim.hexagonal.config.security;

import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    private JwtAuthenticationFilter target;
    private ParseLoginTokenUseCase parseLoginTokenUseCase;
    private ServletRequest servletRequest;
    private ServletResponse servletResponse;
    private FilterChain filterChain;
    private String headerToken;

    @BeforeEach
    void setUp() {
        parseLoginTokenUseCase = mock(ParseLoginTokenUseCase.class);
        target = new JwtAuthenticationFilter(parseLoginTokenUseCase);

        headerToken = "Bearer " + "token";
        servletRequest = new MockHttpServletRequest();
        servletResponse = new MockHttpServletResponse();
        filterChain = mock(FilterChain.class);

        ((MockHttpServletRequest)servletRequest).addHeader("Authorization", headerToken);
        ((MockHttpServletRequest)servletRequest).setMethod(HttpMethod.GET.name());
        ((MockHttpServletRequest)servletRequest).setRequestURI("/api/members");
    }

    @Test
    void 이메일이이존재하지않음() throws ServletException, IOException {
        doReturn(null)
                .when(parseLoginTokenUseCase)
                .parseEmail(headerToken);

        target.doFilter(servletRequest, servletResponse, filterChain);

        verify(filterChain, never()).doFilter(servletRequest, servletResponse);
    }

    @CsvSource({"/api/members/organizers, POST", "/api/login, POST"})
    @ParameterizedTest
    void 인증필터제외되는패턴(final String url, final HttpMethod httpMethod) throws ServletException, IOException {
        ((MockHttpServletRequest)servletRequest).setMethod(httpMethod.name());
        ((MockHttpServletRequest)servletRequest).setRequestURI(url);

        target.doFilter(servletRequest, servletResponse, filterChain);

        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    void 인증필터진행() throws ServletException, IOException {
        doReturn("email")
                .when(parseLoginTokenUseCase)
                .parseEmail(headerToken);

        target.doFilter(servletRequest, servletResponse, filterChain);

        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

}