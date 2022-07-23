package com.mangkyu.moim.hexagonal.app;

import com.mangkyu.moim.hexagonal.acceptance.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@AcceptanceTest
class OrganizerAcceptanceTest {

    @Test
    void 주최자가입성공() {
        final ExtractableResponse<Response> 참여자추가결과 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#");
        가입성공(참여자추가결과);
    }

    @Test
    void 주최자중복되는아이디가입실패() {
        참여자추가("mangkyu1226", "dkssudgktpdy123!@#");

        final ExtractableResponse<Response> 참여자추가 = 참여자추가("mangkyu1226", "dkssudgktpdy123!@#");
        가입실패(참여자추가);
    }

    private void 가입성공(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private void 가입실패(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }


    public static ExtractableResponse<Response> 참여자추가(final String loginId, final String password) {
        return RestAssured.given().log().all()
                .body(addOrganizerParam(loginId, password))
                .contentType(ContentType.JSON)
                .when().post("/api/members/organizer")
                .then().log().all()
                .extract();
    }

    private static Map<String, Object> addOrganizerParam(final String loginId, final String password) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name", "Mangkyu");
        map.put("birth", "19941226");
        map.put("gender", "MALE");
        map.put("loginId", loginId);
        map.put("password", password);
        map.put("email", "mangkyu@test.com");
        map.put("belongs", "MangKyu");

        return map;
    }

}
