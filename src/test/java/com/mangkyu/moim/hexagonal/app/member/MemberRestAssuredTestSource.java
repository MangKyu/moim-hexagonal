package com.mangkyu.moim.hexagonal.app.member;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

import java.util.Map;

public class MemberRestAssuredTestSource {

    public static ExtractableResponse<Response> 구성원추가(final String email, final String password) {
        return RestAssured.given().log().all()
                .body(addMemberParams(email, password))
                .contentType(ContentType.JSON)
                .when().post("/api/members")
                .then().log().all()
                .extract();
    }

    public static Map<String, Object> addMemberParams(final String email, final String password) {
        return Map.of("email", email, "password", password);
    }

}
