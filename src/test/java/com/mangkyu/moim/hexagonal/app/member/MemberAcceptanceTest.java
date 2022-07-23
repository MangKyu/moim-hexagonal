package com.mangkyu.moim.hexagonal.app.member;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class MemberAcceptanceTest {

    // TODO(MinKyu): 사용자 정보들을 확장시켜야 함

    @Test
    void 중복되는이메일가입실패() {
        구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");

        final ExtractableResponse<Response> 중복구성원추가 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#");

        잘못된요청(중복구성원추가);
    }

    @Test
    void 구성원가입성공() {
        final ExtractableResponse<Response> 구성원추가결과 = 구성원추가("mangkyu@naver.com", "dkssudgktpdy123!@#");

        구성원추가성공(구성원추가결과);
    }

    @CsvSource({"mangkyu,adsfaf", ",dkssudgktpdy123!@#", "mangkyu@naver.com,"})
    @ParameterizedTest
    void 구성원가입실패_잘못된파라미터(final String 잘못된이메일, final String 잘못된비밀번호) {
        final Long 구성원 = 구성원추가(잘못된이메일, 잘못된비밀번호).jsonPath().getLong("id");

        final List<Long> 구성원목록조회 = 구성원목록조회();

        assertThat(구성원목록조회).doesNotContain(구성원);
    }

    private void 구성원추가성공(final ExtractableResponse<Response> 구성원추가결과) {
        assertThat(구성원추가결과.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 잘못된요청(final ExtractableResponse<Response> 중복사용자추가) {
        assertThat(중복사용자추가.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    private List<Long> 구성원목록조회() {
        ExtractableResponse<Response> response = RestAssured.given()
                .accept(ContentType.JSON)
                .get("/api/members")
                .then()
                .statusCode(200)
                .extract();

        return response.body().jsonPath().getList("members.id", Long.class);
    }


    private ExtractableResponse<Response> 구성원추가(final String email, final String password) {
        return RestAssured.given().log().all()
                .body(addMemberParams(email, password))
                .contentType(ContentType.JSON)
                .when().post("/api/members")
                .then().log().all()
                .extract();
    }

    private static Map<String, Object> addMemberParams(final String email, final String password) {
        return Map.of("email", email, "password", password);
    }

}
