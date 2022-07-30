package com.mangkyu.moim.hexagonal.app.member.organizer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class OrganizerRestAssuredTestSource {

    public static ExtractableResponse<Response> 주최자역할추가(final Long id, final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .body(addParticipantRoleParam())
                .contentType(ContentType.JSON)
                .when().post("/api/members/organizers/{id}/role", id)
                .then().log().all()
                .extract();
    }

    private static Map<String, Object> addParticipantRoleParam() {
        final Map<String, Object> map = new HashMap<>();
        map.put("belongs", "belongs");
        return map;
    }

    public static ExtractableResponse<Response> 주최자추가(final String loginId, final String password) {
        return RestAssured.given().log().all()
                .body(addOrganizerParam(loginId, password))
                .contentType(ContentType.JSON)
                .when().post("/api/members/organizers")
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
