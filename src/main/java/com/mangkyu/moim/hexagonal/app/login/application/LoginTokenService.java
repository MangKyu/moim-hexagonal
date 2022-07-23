package com.mangkyu.moim.hexagonal.app.login.application;

import com.mangkyu.moim.hexagonal.app.login.domain.in.GenerateLoginTokenUseCase;
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
public class LoginTokenService implements GenerateLoginTokenUseCase {

    private static final String secretKey = "ThisIsA_SecretKeyForMyApplication";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Override
    public String generate(final String email) {
        return Jwts.builder()
                .setSubject(email)
                .setHeader(createHeader())
                .setClaims(createClaims(email))
                .setExpiration(createExpireDateForOneMonth())
                .signWith(signatureAlgorithm, createSigningKey()).compact();
    }

    private Date createExpireDateForOneMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(final Object email) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return claims;
    }

    private Key createSigningKey() {
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

}
