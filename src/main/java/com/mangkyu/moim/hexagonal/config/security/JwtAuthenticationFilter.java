package com.mangkyu.moim.hexagonal.config.security;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter implements Filter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final ParseLoginTokenUseCase parseLoginTokenUseCase;

    private static final Map<String, HttpMethod> patternMap = Map.of(
            "/api/members/organizers", HttpMethod.POST,
            "/api/members/participants", HttpMethod.POST,
            "/api/login", HttpMethod.POST
    );

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isExcludePattern(request)) {
            chain.doFilter(request, response);
            return;
        }

        final String email = findEmailFromRequest(request, response);
        if (!StringUtils.hasText(email)) {
            return;
        }

        request.setAttribute("Email", email);
        chain.doFilter(request, response);
    }

    private String findEmailFromRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            return findEmailFromRequest(authHeader);
        } catch (final Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, CommonErrorCode.UNAUTHORIZED.getMessage());
        }

        return null;
    }

    private String findEmailFromRequest(final String authHeader) {
        final String email = parseLoginTokenUseCase.parseClaims(authHeader);
        if (StringUtils.hasText(email)) {
            return email;
        }

        throw new CommonException(CommonErrorCode.UNAUTHORIZED);
    }

    private boolean isExcludePattern(final HttpServletRequest request) {
        final String requestURI = request.getRequestURI();
        final HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());
        return patternMap.containsKey(requestURI) && patternMap.get(requestURI) == requestMethod;
    }

}
