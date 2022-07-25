package com.mangkyu.moim.hexagonal.app.member.participant;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ParticipantRestAssuredTestSource {

    public static ExtractableResponse<Response> 참여자추가(final String loginId, final String password) {
        return RestAssured.given().log().all()
                .body(addParticipantParam(loginId, password))
                .contentType(ContentType.JSON)
                .when().post("/api/members/participants")
                .then().log().all()
                .extract();
    }

    private static Map<String, Object> addParticipantParam(final String loginId, final String password) {
        final Map<String, Object> map = new HashMap<>();

        map.put("name", "Mangkyu");
        map.put("birth", "19941226");
        map.put("gender", "MALE");
        map.put("loginId", loginId);
        map.put("password", password);
        map.put("email", "mangkyu@test.com");
        map.put("limitedIngredient", "limitedIngredient");
        map.put("introduce", "introduce");

        return map;
    }

}
