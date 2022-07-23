package com.mangkyu.moim.hexagonal.app.login;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.로그인;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerRestAssuredTestSource.주최자추가;
import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class LoginAcceptanceTest {

    private String loginId = "mangkyu1226";
    private String password = "dkssudgktpdy123!@#";

    @BeforeEach
    void setUp() {
        주최자추가(loginId, password);
    }

    @Test
    void 로그인실패_잘못된아이디비밀번호() {
        final ExtractableResponse<Response> 로그인결과 = 로그인("loginId", password);

        로그인실패(로그인결과);
    }

    @Test
    void 로그인성공() {
        final ExtractableResponse<Response> 로그인결과 = 로그인(loginId, password);

        로그인성공(로그인결과);
    }

    private void 로그인실패(final ExtractableResponse<Response> 로그인결과) {
        assertThat(로그인결과.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(로그인결과.body().jsonPath().getString("token")).isNull();
    }

    private void 로그인성공(final ExtractableResponse<Response> 로그인결과) {
        assertThat(로그인결과.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(로그인결과.body().jsonPath().getString("token")).isNotNull();
    }

}
