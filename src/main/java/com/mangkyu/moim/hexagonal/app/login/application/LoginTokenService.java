package com.mangkyu.moim.hexagonal.app.login.application;

import com.mangkyu.moim.hexagonal.app.errors.CommonErrorCode;
import com.mangkyu.moim.hexagonal.app.errors.CommonException;
import com.mangkyu.moim.hexagonal.app.login.domain.in.GenerateLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.login.domain.in.ParseLoginTokenUseCase;
import com.mangkyu.moim.hexagonal.app.member.common.domain.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginTokenService implements GenerateLoginTokenUseCase, ParseLoginTokenUseCase {

    private static final String secretKey = "ThisIsA_SecretKeyForMyApplication";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public String parseClaims(final String token) {
        if (token == null) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }
        final String parsedToken = getTokenFromHeader(token);
        return getUserEmailFromToken(parsedToken);
    }

    private String getTokenFromHeader(String header) {
        final String[] parsedToken = header.split(" ");
        if (parsedToken.length <= 1) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED);
        }

        return parsedToken[1];
    }

    private String getUserEmailFromToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            return (String) claims.get("email");
        } catch (final JwtException exception) {
            throw new CommonException(CommonErrorCode.UNAUTHORIZED, exception);
        }
    }

    private Claims getClaimsFormToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String generate(final Member member) {
        return Jwts.builder()
                .setSubject(member.getLoginId())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDateForOneMonth())
                .signWith(signatureAlgorithm, createSigningKey()).compact();
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
