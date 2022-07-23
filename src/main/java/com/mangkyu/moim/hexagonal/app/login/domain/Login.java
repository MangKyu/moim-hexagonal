package com.mangkyu.moim.hexagonal.app.login.domain;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
public class Login {

    private String email;
    private String password;

    private static final String secretKey = "ThisIsA_SecretKeyForMyApplication";
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public boolean passwordMatches(final PasswordEncoder passwordEncoder, final String password) {
        return passwordEncoder.matches(this.password, password);
    }

    public String generateToken() {
        return Jwts.builder()
                .setSubject(email)
                .setHeader(createHeader())
                .setClaims(createClaims())
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

    private Map<String, Object> createClaims() {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        return claims;
    }

    private Key createSigningKey() {
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
    }

}
