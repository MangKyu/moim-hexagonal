package com.mangkyu.moim.hexagonal.config.security;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginTokenClaims;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

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

        final LoginTokenClaims claims = findClaimsFromRequest(request, response);
        if (claims == null) {
            return;
        }

        request.setAttribute("claims", claims);
        chain.doFilter(request, response);
    }

    private LoginTokenClaims findClaimsFromRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        try {
            final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            return findClaimsFromRequest(authHeader);
        } catch (final Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, CommonErrorCode.UNAUTHORIZED.getMessage());
        }

        return null;
    }

    private LoginTokenClaims findClaimsFromRequest(final String authHeader) {
        final LoginTokenClaims claims = parseLoginTokenUseCase.parseClaims(authHeader);
        if (claims != null) {
            return claims;
        }

        throw new CommonException(CommonErrorCode.UNAUTHORIZED);
    }

    private boolean isExcludePattern(final HttpServletRequest request) {
        final String requestURI = request.getRequestURI();
        final HttpMethod requestMethod = HttpMethod.valueOf(request.getMethod());
        return patternMap.containsKey(requestURI) && patternMap.get(requestURI) == requestMethod;
    }

}
