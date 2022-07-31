package com.mangkyu.moim.hexagonal.app.login.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String accessToken;

    @Column(length = 1024)
    private String refreshToken;
    private Long expiresIn;
    private String tokenType;

}
