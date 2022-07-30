package com.mangkyu.moim.hexagonal.app.member.common;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class MemberRestAssuredTestSource {

    public static ExtractableResponse<Response> 구성원조회(final String token) {
        return RestAssured.given().log().all()
                .auth().oauth2(token)
                .when().get("/api/members/me")
                .then().log().all()
                .extract();
    }

}
