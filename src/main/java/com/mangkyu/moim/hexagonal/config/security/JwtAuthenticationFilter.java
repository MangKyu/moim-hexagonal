package com.mangkyu.moim.hexagonal.config.security;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter implements Filter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final ParseLoginTokenUseCase parseLoginTokenUseCase;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws ServletException, IOException {
        final String authHeader = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);
        final String email = parseLoginTokenUseCase.parseEmail(authHeader);
        if (!StringUtils.hasText(email)) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }

        chain.doFilter(request, response);
    }

}
