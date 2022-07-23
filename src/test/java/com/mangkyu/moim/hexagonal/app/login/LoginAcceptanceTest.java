package com.mangkyu.moim.hexagonal.app.login;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.로그인;
import static com.mangkyu.moim.hexagonal.app.member.MemberRestAssuredTestSource.구성원추가;
import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class LoginAcceptanceTest {

    private String email = "mangkyu@naver.com";
    private String password = "dkssudgktpdy123!@#";

    @BeforeEach
    void setUp() {
        구성원추가(email, password);
    }

    @CsvSource({"mangkyu@test.com, dkssudgktpdy123!@#", "mangkyu@naver.com,dkssudgktpdy123!@#!@#"})
    @ParameterizedTest
    void 로그인실패_잘못된로그인정보(final String email, final String password) {
        final ExtractableResponse<Response> 로그인결과 = 로그인(email, password);

        로그인실패(로그인결과);
    }

    @Test
    void 로그인성공() {
        final ExtractableResponse<Response> 로그인결과 = 로그인(email, password);

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
