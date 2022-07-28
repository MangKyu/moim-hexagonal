package com.mangkyu.moim.hexagonal.app.member.common;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.구성원조회;
import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.로그인토큰;
import static com.mangkyu.moim.hexagonal.app.member.organizer.OrganizerRestAssuredTestSource.주최자추가;
import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class MemberAcceptanceTest {

    @Test
    void 비밀번호변경실패_본인이아님() {
        주최자추가("temp1226@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 로그인토큰 = 로그인토큰("temp1226@naver.com", "dkssudgktpdy123!@#");

        final Long 구성원 = 주최자추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final ExtractableResponse<Response> 구성원조회결과 = 구성원비밀번호변경(구성원, 로그인토큰, "qlalfqjsgh123!@#");

        잘못된토큰으로요청(구성원조회결과);
    }

    @Test
    void 비밀번호변경성공() {
        final Long 구성원 = 주최자추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 로그인토큰 = 로그인토큰("mangkyu@naver.com", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 비밀번호변경결과 = 구성원비밀번호변경(구성원, 로그인토큰, "qlalfqjsgh123!@#");
        final String 변경후토큰 = 로그인토큰("mangkyu@naver.com", "qlalfqjsgh123!@#");
//        final String 변경후토큰 = 로그인토큰("mangkyu@naver.com", "dkssudgktpdy123!@#");

        비밀번호변경성공(비밀번호변경결과, 변경후토큰);
    }

    @Disabled
    @Test
    void 구성원정보조회실패_잘못된토큰() {
        final Long 구성원 = 주최자추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final ExtractableResponse<Response> 구성원조회결과 = 구성원조회(구성원, "Invalid token");

        잘못된토큰으로요청(구성원조회결과);
    }

    @Disabled
    @Test
    void 구성원정보조회성공() {
        final Long 구성원 = 주최자추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 로그인토큰 = 로그인토큰("mangkyu@naver.com", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 구성원조회결과 = 구성원조회(구성원, 로그인토큰);
    }

    private void 비밀번호변경성공(final ExtractableResponse<Response> 구성원조회결과, final String 토큰) {
        assertThat(구성원조회결과.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(토큰).isNotNull();
    }

    private void 잘못된토큰으로요청(final ExtractableResponse<Response> 구성원조회결과) {
        assertThat(구성원조회결과.statusCode()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    private ExtractableResponse<Response> 구성원비밀번호변경(final Long id, final String token, final String password) {
        return RestAssured.given().log().all()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(Map.of("password", password))
                .when().put("/api/members/{id}/password", id)
                .then().log().all()
                .extract();
    }

}
