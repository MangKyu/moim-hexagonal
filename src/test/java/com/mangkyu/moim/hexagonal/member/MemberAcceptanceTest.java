package com.mangkyu.moim.hexagonal.member;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class MemberAcceptanceTest {

    // TODO(MinKyu): 사용자 정보들을 확장시켜야 함

    @Test
    void 사용자추가성공() {
        final Long 사용자 = 사용자추가("mangkyu@naver.com", "dkssudgktpdy123!@#").jsonPath().getLong("id");

        final List<Long> 사용자목록조회 = 사용자목록조회();

        assertThat(사용자목록조회).containsExactly(사용자);
    }

    @CsvSource({"mangkyu,adsfaf", ",dkssudgktpdy123!@#", "mangkyu@naver.com,"})
    @ParameterizedTest
    void 사용자추가실패_잘못된파라미터(final String 잘못된이메일, final String 잘못된비밀번호) {
        final Long 사용자 = 사용자추가(잘못된이메일, 잘못된비밀번호).jsonPath().getLong("id");

        final List<Long> 사용자목록조회 = 사용자목록조회();

        assertThat(사용자목록조회).doesNotContain(사용자);
    }

    private List<Long> 사용자목록조회() {
        ExtractableResponse<Response> response = RestAssured.given()
                .accept(ContentType.JSON)
                .get("/api/members")
                .then()
                .statusCode(200)
                .extract();

        return response.body().jsonPath().getList("members.id", Long.class);
    }


    private ExtractableResponse<Response> 사용자추가(final String email, final String password) {
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
