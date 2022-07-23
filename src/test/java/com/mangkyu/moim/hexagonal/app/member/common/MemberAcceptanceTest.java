package com.mangkyu.moim.hexagonal.app.member.common;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.구성원조회;
import static com.mangkyu.moim.hexagonal.app.login.LoginRestAssuredTestSource.로그인토큰;
import static com.mangkyu.moim.hexagonal.app.member.common.MemberRestAssuredTestSource.구성원추가;
import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@AcceptanceTest
class MemberAcceptanceTest {

    // TODO(MinKyu): 사용자 정보들을 확장시켜야 함

    @Test
    void 중복되는이메일가입실패() {
        구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");

        final ExtractableResponse<Response> 중복구성원추가 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#");

        잘못된요청(중복구성원추가);
    }

    @CsvSource({"mangkyu,adsfaf", ",dkssudgktpdy123!@#", "mangkyu@naver.com,"})
    @ParameterizedTest
    void 구성원가입실패_잘못된파라미터(final String 잘못된이메일, final String 잘못된비밀번호) {
        final ExtractableResponse<Response> 구성원추가결과 = 구성원추가(잘못된이메일, 잘못된비밀번호);

        잘못된요청(구성원추가결과);
    }

    @Test
    void 구성원가입성공() {
        final ExtractableResponse<Response> 구성원추가결과 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#");

        구성원추가성공(구성원추가결과);
    }

    @Test
    void 구성원정보조회실패_잘못된토큰() {
        final Long 구성원 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final ExtractableResponse<Response> 구성원조회결과 = 구성원조회(구성원, "Invalid token");

        잘못된토큰으로요청(구성원조회결과);
    }

    @Test
    void 구성원정보조회성공() {
        final Long 구성원 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");
        final String 로그인토큰 = 로그인토큰("mangkyu@naver.com", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 구성원조회결과 = 구성원조회(구성원, 로그인토큰);
        구성원조회성공(구성원조회결과);
    }

    private void 구성원조회성공(final ExtractableResponse<Response> 구성원조회결과) {
        assertThat(구성원조회결과.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private void 잘못된토큰으로요청(final ExtractableResponse<Response> 구성원조회결과) {
        assertThat(구성원조회결과.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

    private void 구성원추가성공(final ExtractableResponse<Response> 구성원추가결과) {
        assertThat(구성원추가결과.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 잘못된요청(final ExtractableResponse<Response> 중복사용자추가) {
        assertThat(중복사용자추가.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

}
