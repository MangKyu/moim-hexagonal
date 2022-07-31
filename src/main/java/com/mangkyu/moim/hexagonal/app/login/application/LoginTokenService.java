package com.mangkyu.moim.hexagonal.app.login.application;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginMember;
import com.mangkyu.moim.hexagonal.app.login.domain.LoginToken;
import com.mangkyu.moim.hexagonal.app.login.domain.in.GenerateLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Gender;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import com.mangkyu.moim.hexagonal.app.member.common.domain.MemberRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LoginTokenService implements GenerateLoginTokenUseCase, ParseLoginTokenUseCase {

    private static final String secretKey = "ThisIsA_SecretKeyForMyApplication";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public LoginMember parseClaims(final String token) {
        if (token == null) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }
        final String parsedToken = getTokenFromHeader(token);
        return createClaimsFromToken(parsedToken);
    }

    private String getTokenFromHeader(String header) {
        final String[] parsedToken = header.split(" ");
        if (parsedToken.length <= 1) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }

        return parsedToken[1];
    }

    private LoginMember createClaimsFromToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            return LoginMember.builder()
                    .id(claims.get("id", Long.class))
                    .gender(Gender.valueOf(claims.get("gender", String.class)))
                    .email(claims.get("email", String.class))
                    .loginId(claims.get("loginId", String.class))
                    .roles(createRoles(claims))
                    .build();
        } catch (final JwtException exception) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED, exception);
        }
    }

    private Set<MemberRole> createRoles(final Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(MemberRole::valueOf)
                .collect(Collectors.toSet());
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public LoginToken generate(final Member member) {
        return LoginToken.builder()
                .accessToken(generate(member, createExpireDateForOneDay()))
                .refreshToken(generate(member, createExpireDateForOneMonth()))
                .tokenType("Bearer")
                .expiresIn(Duration.ofDays(1).toSeconds())
                .build();
    }

    private String generateToken(final Member member) {
        return Jwts.builder()
                .setSubject(member.getLoginId())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDateForOneMonth())
                .signWith(signatureAlgorithm, createSigningKey()).compact();
    }

    private String generate(final Member member, final Date date) {
        return Jwts.builder()
                .setSubject(member.getLoginId())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(date)
                .signWith(signatureAlgorithm, createSigningKey()).compact();
    }

    private Date createExpireDateForOneDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 24);
        return c.getTime();
    }

    private Date createExpireDateForOneMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 24);
        return c.getTime();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(final Member member) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("id", member.getId());
        claims.put("loginId", member.getLoginId());
        claims.put("gender", member.getGender());
        claims.put("email", member.getEmail());
        claims.put("roles", member.getRoles());
        return claims;
    }

    private Key createSigningKey() {
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

}
